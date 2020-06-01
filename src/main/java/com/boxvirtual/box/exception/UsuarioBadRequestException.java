package com.boxvirtual.box.exception;


public class UsuarioBadRequestException extends RuntimeException{
    public UsuarioBadRequestException(String estado) {

        super("No valido" + estado );
    }


}