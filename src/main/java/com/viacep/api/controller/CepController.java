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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.viacep.api.constant.CepConstant.*;

@RestController
@RequestMapping("v1/consult-cep")
public class CepController {

    public static String URL = "https://viacep.com.br/ws/%s/json";
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
            ResponseEntity<Cep> response = restTemplate.getForEntity(String.format(URL, cep), Cep.class);

            if (Objects.requireNonNull(response.getBody()).getCep() == null) {
                logger.info(CEP_NOT_EXIST + cep);
                return ResponseEntity.status(HttpStatus.OK).body(CEP_NOT_EXIST);
            }

            logger.info(CEP_SEARCH + cep);
            logger.info(CEP_SAVED + cep);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(response.getBody()));

        }

        logger.info(CEP_DATABASE + cep);
        return ResponseEntity.status(HttpStatus.OK).body(cepModel.get());
    }

    @GetMapping
    public ResponseEntity<Object> consultAllCepDataBase() {
        List<Cep> cep = service.findAllCep();

        if (cep.isEmpty()) {
            logger.info(DATABASE_EMPTY);
            return ResponseEntity.status(HttpStatus.OK).body(DATABASE_EMPTY);
        }

        logger.info(CEP_TOTAL + cep.size());
        return ResponseEntity.status(HttpStatus.OK).body(cep);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllCep() {
        List<Cep> cep = service.findAllCep();

        if (cep.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CEP_NOT_EXIST);
        }

        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(CEP_TOTAL_DELETED + cep.size());
    }
}
