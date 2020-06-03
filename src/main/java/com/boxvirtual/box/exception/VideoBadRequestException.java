package com.boxvirtual.box.exception;


public class VideoBadRequestException extends RuntimeException{
    public VideoBadRequestException(String estado) {

        super("No valido" + estado );
    }


}