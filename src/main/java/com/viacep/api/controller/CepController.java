package com.viacep.api.controller;

import com.viacep.api.model.Cep;
import com.viacep.api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.viacep.api.constant.CepConstant.*;

@RestController
@RequestMapping("v1/consult-cep")
public class CepController {
    @Autowired
    private final CepService service;
    public CepController(CepService service) {
        this.service = service;
    }
    @GetMapping("{cep}")
    public ResponseEntity<Object> consultCep(@PathVariable("cep") String cep) throws Exception {
        Cep result = service.findCepDB(cep);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping
    public ResponseEntity<Object> consultAllCepDataBase() throws Exception {
        try {
            List<Cep> cep = service.findAllCep();
            return ResponseEntity.status(HttpStatus.OK).body(cep);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
    @DeleteMapping
    public ResponseEntity<Object> deleteAllCep() throws Exception {
        try {
            List<Cep> cep = service.findAllCep();
            service.deleteAll();
            return ResponseEntity.status(HttpStatus.OK).body(CEP_TOTAL_DELETED + cep.size());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());

        }
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Object> deleteUuid(@PathVariable("id") UUID id) throws Exception {
        try {
            service.deleteCepByUuid(id);
            return ResponseEntity.status(HttpStatus.OK).body(CEP_DELETED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
    @DeleteMapping("/cep/{cep}")
    public ResponseEntity<Object> deleteCep(@PathVariable("cep") String cep) throws Exception {
        try {
            service.deleteCepByCep(cep);
            return ResponseEntity.status(HttpStatus.OK).body(CEP_DELETED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }

    }
}
