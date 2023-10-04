package com.viacep.api.controller;

import com.viacep.api.model.Cep;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("consult-cep")
public class CepController {

    @GetMapping("{cep}")
    public Cep consultCep(@PathVariable("cep") String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cep> response = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), Cep.class);

        return response.getBody();
    }
}
