package com.boxvirtual.box.exception;


public class VideoNoExistException extends RuntimeException{
    public VideoNoExistException(Long idUsuario ) {
        super("La llamada id usuario: " + idUsuario +" no existe");
    }
}
