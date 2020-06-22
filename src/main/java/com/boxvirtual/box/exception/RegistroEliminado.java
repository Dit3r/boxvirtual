package com.boxvirtual.box.exception;


public class UsuarioEliminado extends RuntimeException{
    public UsuarioEliminado(Long rut ) {
        super("El Usuario con rut: " + rut +" fue eliminado");
    }
}
