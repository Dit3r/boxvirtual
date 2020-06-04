package com.boxvirtual.box.controller;

import com.boxvirtual.box.domain.datosMedicos;
import com.boxvirtual.box.domain.usuario;
import com.boxvirtual.box.domain.video;
import com.boxvirtual.box.exception.DatosNotFoundException;
import com.boxvirtual.box.exception.UsuarioNoExistException;
import com.boxvirtual.box.exception.UsuarioNotFoundException;
import com.boxvirtual.box.repository.DatosRepository;
import com.boxvirtual.box.repository.UsuarioRepository;
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
@Api(value = "datosMedicos")
@RequestMapping("/datosMedicos")
public class DatosController {

    private static final Logger logger = LoggerFactory.getLogger(DatosController.class);
    private static final String DATOS = "Datos";




    @Autowired
    private DatosRepository DatosRepository;




    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "crear datos medicos",
            notes = "crear datos medicos" ,
            response = datosMedicos.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Daros Creados"),
            @ApiResponse(code = 400,message = "Falta el atributo"),
            @ApiResponse(code = 500,message = "Error interno del servidor"),
            @ApiResponse(code = 409,message = "La carta ya existe")
    })
    public ResponseEntity add(@RequestBody datosMedicos datosMedicos)throws URISyntaxException {
        logger.info("intento creación de datos medicos");


        datosMedicos newDatos = DatosRepository.save(datosMedicos);
        logger.info("Datos Creados");
        Resource<datosMedicos> resource = new Resource<>(newDatos,
                linkTo(methodOn(DatosController.class).one(newDatos.getId())).withSelfRel(),
                linkTo(methodOn(DatosController.class).all()).withRel(DATOS));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener todas los datos medicos de los usuarios",
            notes = "obtener todas los datos medicos de los usuarios" ,
            response = datosMedicos.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "listado de datos medicos"),
            @ApiResponse(code = 405,message = "Recurso no existe"),
            @ApiResponse(code = 500,message = "Error interno de servidor")
    })
    public Resources<Resource<datosMedicos>> all(){
        logger.info("obtencion de todos datos medicos de usuarios");

        List<Resource<datosMedicos>> datos = DatosRepository.findAll().stream()
                   .map(datosMedicos -> new Resource<>(datosMedicos,
                           linkTo(methodOn(DatosController.class).one(datosMedicos.getId())).withSelfRel()))
                   .collect(Collectors.toList());


        return new Resources<>(datos);

    }




    @GetMapping(path = "/allusuario/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener cartas  num orden y despachador",
            notes = "obtener cartas  num orden y despachador" ,
            response = datosMedicos.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Carta encontrada"),
            @ApiResponse(code = 405, message = "Recurso no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor")
    })
    public Resources<Resource<datosMedicos>> all(
            @PathVariable("idUsuario") Long idUsuario){
        logger.info("obtener datos medicos del usuario id {}",idUsuario);


        List<Resource<datosMedicos>> datos = DatosRepository.findByIdUsuarioDatos(idUsuario).stream()
                .map(dat -> new Resource<>(dat,

                        linkTo(methodOn(DatosController.class).one(dat.getId())).withSelfRel()))
                .collect(Collectors.toList());


        return new Resources<>(datos);
    }






    @GetMapping(path = "/ultimo/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener ultimo dato medico con id suario",
            notes = "obtener ultimo dato medico con id suario" ,
            response = datosMedicos.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Dato encontrado"),
            @ApiResponse(code = 405, message = "Recurso no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor")
    })
    public Resource<datosMedicos> one(@PathVariable("idUsuario") Long idUsuario ){
        logger.info("Obtener ultimos datos medicos con id {}", idUsuario);

        Long id = DatosRepository.findByIdUsuarioDatosUltimo(idUsuario).getId();


        if(!DatosRepository.findById(id).isPresent() ) {
            throw new UsuarioNoExistException(idUsuario);
        }

        datosMedicos datosMedicos = DatosRepository.findById(id)
                .orElseThrow(() -> new DatosNotFoundException(idUsuario));

        List<Link> collect = new ArrayList<>();
        collect.add(linkTo(methodOn(DatosController.class).one(idUsuario)).withSelfRel());
        collect.add(linkTo(methodOn(DatosController.class).all()).withRel(DATOS));
        return new Resource<>(datosMedicos, collect);
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
