package com.boxvirtual.box.controller;

import com.boxvirtual.box.domain.usuario;
import com.boxvirtual.box.exception.UsuarioNoExistException;
import com.boxvirtual.box.exception.UsuarioNotFoundException;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@Api(value = "usuarios")
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private static final String USUARIO = "Usuarios";




    @Autowired
    private UsuarioRepository UsuarioRepository;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener todas las ordenes de compra",
            notes = "obtener todas las Ordenes de compra" ,
            response = usuario.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "listado de Ordenes"),
            @ApiResponse(code = 405,message = "Recurso no existe"),
            @ApiResponse(code = 500,message = "Error interno de servidor")
    })
    public Resources<Resource<usuario>> all(){
        logger.info("obtencion de todos los usuarios");

        List<Resource<usuario>> users = UsuarioRepository.findAll().stream()
                   .map(usuario -> new Resource<>(usuario,
                           linkTo(methodOn(UsuarioController.class).one(usuario.getId())).withSelfRel()))
                   .collect(Collectors.toList());


        return new Resources<>(users);

    }





    @GetMapping(path = "/{rut}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ApiOperation(value = "obtener una oc con id",
            notes = "obtener una oc con id" ,
            response = usuario.class, responseContainer = "pos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario encontrado"),
            @ApiResponse(code = 405, message = "Recurso no existe"),
            @ApiResponse(code = 500, message = "Error interno de servidor")
    })
    public Resource<usuario> one(@PathVariable("rut") Long rut ){
        logger.info("obtener OC por id {}", rut);

        if(!UsuarioRepository.findById(rut).isPresent() ) {
            throw new UsuarioNoExistException(rut);
        }

        usuario usuario = UsuarioRepository.findById(rut)
                .orElseThrow(() -> new UsuarioNotFoundException(rut));

        List<Link> collect = new ArrayList<>();
        collect.add(linkTo(methodOn(UsuarioController.class).one(rut)).withSelfRel());
        collect.add(linkTo(methodOn(UsuarioController.class).all()).withRel(USUARIO));
        return new Resource<>(usuario, collect);
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
