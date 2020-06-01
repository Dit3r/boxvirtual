package com.boxvirtual.box.controller;



import com.boxvirtual.box.exception.UsuarioBadRequestException;
import com.boxvirtual.box.exception.UsuarioNoExistException;
import com.boxvirtual.box.exception.UsuarioNotFoundException;
import com.boxvirtual.box.resources.respuesta;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UsuarioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<?> cotizacionNotFoundHandler(UsuarioNotFoundException ex) {
        return new ResponseEntity(new respuesta(ex.getMessage(), "Not Found", 404)
                                , HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = {DataIntegrityViolationException.class, UsuarioNoExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<?> handleConflict(Exception ex) {
        return new ResponseEntity(new respuesta(ex.getMessage(), "Conflict", 409)
                , HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(UsuarioBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleBadRequest(Exception ex) {
        return new ResponseEntity(new respuesta(ex.getMessage(), "Bad request", 400)
                , HttpStatus.BAD_REQUEST);
    }


}
