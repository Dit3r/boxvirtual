package com.boxvirtual.box.exception;


public class UsuarioNoExistException extends RuntimeException{
    public UsuarioNoExistException(Long rut ) {
        super("El Usuario con rut: " + rut +" no existe");
    }
}
