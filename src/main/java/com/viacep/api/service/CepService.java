package com.viacep.api.service;

import com.viacep.api.model.Cep;
import com.viacep.api.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    @Autowired
    private CepRepository repository;

    public Object save(Cep cepModel) {
        return repository.save(cepModel);
    }

}
