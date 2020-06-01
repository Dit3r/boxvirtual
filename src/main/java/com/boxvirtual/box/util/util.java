package com.boxvirtual.box.util;


import org.owasp.esapi.ESAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class util {
    public static String encode(String message) {
        message = message.replace( '\n' ,  '_' ).replace( '\r' , '_' )
                .replace( '\t' , '_' );
        message = ESAPI.encoder().encodeForHTML( message );
        return message;
    }


    public static String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public static Date formatDate(){
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        String DateToStr = format.format(curDate);
        Date strToDate = null;
        try {
            strToDate = format.parse(DateToStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strToDate;
    }
}
