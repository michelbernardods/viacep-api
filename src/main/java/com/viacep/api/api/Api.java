package com.viacep.api.api;

import com.viacep.api.model.Cep;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Api {
    public final static String URL = "https://viacep.com.br/ws/%s/json";
    private final RestTemplate restTemplate;
    public Api(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate =  restTemplateBuilder.build();
    }
    public Cep getCep(String cep) {
        ResponseEntity<Cep> response = restTemplate.getForEntity(String.format(URL, cep), Cep.class);
        return response.getBody();
    }
}
