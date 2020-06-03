package com.boxvirtual.box.exception;

public class VideoNotFoundException extends RuntimeException{
    public VideoNotFoundException(Long id) {
        super("No se pudo encontrar el video ID:  " + id);
    }
}
