package com.boxvirtual.box.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix="healthcheck")
public class HeathCheckList {
    private final List<String> ws = new ArrayList<>();
    public List<String> getWs() {
        return ws;
    }
}
