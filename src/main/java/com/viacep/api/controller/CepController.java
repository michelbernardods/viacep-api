package com.viacep.api.controller;

import com.viacep.api.model.Cep;
import com.viacep.api.service.CepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("consult-cep")
public class CepController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("{cep}")
    public ResponseEntity<Object> consultCep(@PathVariable("cep") String cep) {
        Optional<Cep> cepModel = service.findCep(cep);

        if (cepModel.isEmpty()) {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Cep> response = restTemplate.getForEntity(String.format("https://viacep.com.br/ws/%s/json", cep), Cep.class);

            if (Objects.requireNonNull(response.getBody()).getCep() == null) {
                logger.info("CEP is not exist: {}", cep);
                return ResponseEntity.status(HttpStatus.OK).body("CEP is not exist");
            }

            logger.info("Consulting cep in API: {}", cep);
            logger.info("New Cep saved in database: {}", cep);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response.getBody()));

        }

        logger.info("Get Cep in database: {}", cep);
        return ResponseEntity.status(HttpStatus.OK).body(cepModel.get());
    }
}
