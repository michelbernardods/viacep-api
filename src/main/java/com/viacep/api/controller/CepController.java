package com.viacep.api.controller;

import com.viacep.api.model.Cep;
import com.viacep.api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("consult-cep")
public class CepController {

    @Autowired
    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("{cep}")
    public ResponseEntity<Object> consultCep(@PathVariable("cep") String cep) {
        Optional<Cep> cepId = service.findCep(cep);

        if (cepId.isEmpty()) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Cep> response = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), Cep.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response.getBody()));

        }

        return ResponseEntity.status(HttpStatus.OK).body(cepId.get());
    }
}
