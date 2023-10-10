package com.viacep.api.service;

import com.viacep.api.api.Api;
import com.viacep.api.model.Cep;
import com.viacep.api.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CepService {

    @Autowired
    public Api api;
    @Autowired
    private CepRepository repository;

    public Cep findCepApi(String cep) {
        return api.getCep(cep);
    }
    public Optional<Cep> findCepDB(String cep) {
        return repository.findByCep(cep);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Object save(Cep cepModel) {
        return repository.save(cepModel);
    }

    public List<Cep> findAllCep() {
        return repository.findAll();
    }
}
