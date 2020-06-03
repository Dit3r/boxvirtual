package com.boxvirtual.box.controller;

import com.boxvirtual.box.domain.usuario;
import com.boxvirtual.box.domain.video;
import com.boxvirtual.box.exception.*;
import com.boxvirtual.box.repository.VideoRepository;
import com.boxvirtual.box.util.util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(value = "usuarios")
@RequestMapping("/video")
public class VideoController {

    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
    private static final String VIDEO = "video";




    @Autowired
    private VideoRepository VideoRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener todas las videollamadas a",
            notes = "obtener todas las llamadas" ,
            response = video.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "listado de videos"),
            @ApiResponse(code = 405,message = "Recurso no existe"),
            @ApiResponse(code = 500,message = "Error interno de servidor")
    })
    public Resources<Resource<video>> all(){
        logger.info("obtencion de todos las llamadas");

        List<Resource<video>> videos = VideoRepository.findAll().stream()
                   .map(video -> new Resource<>(video,
                           linkTo(methodOn(VideoController.class).one(video.getId())).withSelfRel()))
                   .collect(Collectors.toList());


        return new Resources<>(videos);

    }



    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener un video ",
            notes = "obtener una oc con id" ,
            response = video.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario encontrado"),
            @ApiResponse(code = 405, message = "Recurso no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor")
    })
    public Resource<video> one(@PathVariable("id") Long id ){
        logger.info("obtener Usuarios por id {}", id);

        if(!VideoRepository.findById(id).isPresent() ) {
            throw new VideoNoExistException(id);
        }

        video video = VideoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));

        List<Link> collect = new ArrayList<>();
        collect.add(linkTo(methodOn(UsuarioController.class).one(id)).withSelfRel());
        collect.add(linkTo(methodOn(UsuarioController.class).all()).withRel(VIDEO));
        return new Resource<>(video, collect);
    }




    @GetMapping(path = "ultimovideo/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener ultimo con id",
            notes = "obtener una oc con id" ,
            response = video.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario encontrado"),
            @ApiResponse(code = 405, message = "Recurso no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor")
    })
    public Resource<video> all(@PathVariable("idUsuario") Long idUsuario ){
        logger.info("obtener Usuarios por id {}", idUsuario);

        Long id = VideoRepository.findByIdUsuarioUltimo(idUsuario).getId();

        if(!VideoRepository.findById(id).isPresent() ) {
            throw new VideoNoExistException(idUsuario);
        }

        video video = VideoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(id));

        List<Link> collect = new ArrayList<>();
        collect.add(linkTo(methodOn(VideoController.class).one(id)).withSelfRel());
        collect.add(linkTo(methodOn(VideoController.class).all()).withRel(VIDEO));
        return new Resource<>(video, collect);
    }



    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "crear carta",
            notes = "crear carta" ,
            response = video.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Carta creada con exito"),
            @ApiResponse(code = 400,message = "Falta el atributo"),
            @ApiResponse(code = 500,message = "Error interno del servidor"),
            @ApiResponse(code = 409,message = "La carta ya existe")
    })
    public ResponseEntity add(@RequestBody video video)throws URISyntaxException {
        logger.info("intento creación de video");


        video newVideo = VideoRepository.save(video);
        logger.info("Carta creada");
        Resource<video> resource = new Resource<>(newVideo,
                linkTo(methodOn(VideoController.class).one(newVideo.getId())).withSelfRel(),
                linkTo(methodOn(VideoController.class).all()).withRel(VIDEO));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }



    @PatchMapping("update/{idUsuario}")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "actualizar  el estado de un video",
            notes = "actualizar  el estado de un video" , responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "video actualizado"),
            @ApiResponse(code = 404, message = "video no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor"),
            @ApiResponse(code = 400, message = "Falta atributo o valor incorrecto")
    })
    Resource<video> patch(@RequestBody video inVideo, @PathVariable Long idUsuario) throws URISyntaxException {
        if (inVideo.getEstado() > 1 || inVideo.getEstado() < 0 )
            throw new VideoBadRequestException("El Valor estado "+ inVideo.getEstado()+" es incorrecto. Ingresar: 1 = activo, 0 = No avtivo" );
        Long id = VideoRepository.findByIdUsuarioUltimo(idUsuario).getId();

        video patch = VideoRepository.findById(id)
                .map(video -> {

                    video.setEstado(inVideo.getEstado());
                    //todo:en duda si se cambia el login category

                    return VideoRepository.save(video);
                })
                .orElseThrow(() -> new VideoNotFoundException(idUsuario));

        return new Resource<>(patch,
                linkTo(methodOn(VideoController.class).one(patch.getId())).withSelfRel(),
                linkTo(methodOn(VideoController.class).all()).withRel(VIDEO));
    }



    @DeleteMapping("/{rut}")
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    @ApiOperation(value = "eliminar una oc NO IMPLEMTADO",
            notes = "eliminar una oc No Implementado" , responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "método no impementado"),
    })
    ResponseEntity del(@PathVariable Long rut) {
        return null;
    }


}
