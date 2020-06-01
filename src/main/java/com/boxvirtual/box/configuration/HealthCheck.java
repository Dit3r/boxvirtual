package com.boxvirtual.box.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.List;

@Component
public class HealthCheck implements HealthIndicator {

    @Autowired
    private HeathCheckList heathCheckList;


    @Override
    public Health health() {
        String errorString = "Error:";
        int errorCode = 0;

        List<String> urls = heathCheckList.getWs();
        for(String url: urls){
            if(getCodeStatusByURL(url) != 200) {
                errorString+=" - Fails in "+url;
                errorCode++;
            }
        }
        if (errorCode != 0) {
            return Health.down()
                    .withDetail(errorString, errorCode).build();
        }
        return Health.up().build();
    }

    private int getCodeStatusByURL(String url){
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        try {
            ResponseEntity<String> respuesta = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);
            return respuesta.getStatusCodeValue();
        } catch (Exception e){
            return 500;


        }
    }
}

