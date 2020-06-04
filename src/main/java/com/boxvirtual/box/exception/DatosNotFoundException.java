package com.boxvirtual.box.exception;

public class DatosNotFoundException extends RuntimeException{
    public DatosNotFoundException(Long num) {
        super("No se pudo encontrar los datos de :  " + num);
    }
}
