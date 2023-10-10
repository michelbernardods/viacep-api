package com.viacep.api.controller;

import com.viacep.api.api.Api;
import com.viacep.api.model.Cep;
import com.viacep.api.service.CepService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.viacep.api.constant.CepConstant.*;

@RestController
@RequestMapping("v1/consult-cep")
public class CepController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final CepService service;

    public CepController(CepService service) {
        this.service = service;
    }

    @GetMapping("{cep}")
    public ResponseEntity<Object> consultCep(@PathVariable("cep") String cep) {
        Optional<Cep> cepModel = service.findCepDB(cep);

        if (cepModel.isEmpty()) {
            Cep responseApi = service.findCepApi(cep);
            if (responseApi.getCep() == null) {
                logger.info(CEP_NOT_EXIST + cep);
                return ResponseEntity.status(HttpStatus.OK).body(CEP_NOT_EXIST);
            }

            logger.info(CEP_SEARCH + cep);
            logger.info(CEP_SAVED + cep);
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(responseApi));

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
            logger.info(DATABASE_EMPTY);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DATABASE_EMPTY);
        }

        service.deleteAll();
        logger.info(CEP_TOTAL_DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(CEP_TOTAL_DELETED + cep.size());
    }

@DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUuid(@PathVariable("id") UUID id) {
        Optional<Cep> cep = service.findById(id);

        if (cep.isEmpty()) {
            logger.info(CEP_NOT_EXIST);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(CEP_NOT_EXIST);
        }

        service.deleteCepByUuid(id);
        logger.info(CEP_DELETED);
        return ResponseEntity.status(HttpStatus.OK).body(CEP_DELETED);
    }
}
