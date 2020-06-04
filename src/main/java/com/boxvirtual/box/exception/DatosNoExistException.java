package com.boxvirtual.box.exception;


public class DatosNoExistException extends RuntimeException{
    public DatosNoExistException(Long num ) {
        super("Los datos : " + num +" no existen");
    }
}
