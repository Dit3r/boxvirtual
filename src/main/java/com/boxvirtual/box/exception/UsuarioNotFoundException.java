package com.boxvirtual.box.exception;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(Long rut) {
        super("No se pudo encontrar el usuario rut:  " + rut);
    }
}
