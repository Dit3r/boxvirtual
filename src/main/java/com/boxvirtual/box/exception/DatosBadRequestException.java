package com.boxvirtual.box.exception;


public class DatosBadRequestException extends RuntimeException{
    public DatosBadRequestException(String estado) {

        super("No valido" + estado );
    }


}