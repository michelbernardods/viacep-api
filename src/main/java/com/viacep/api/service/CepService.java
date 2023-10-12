package com.viacep.api.service;

import com.viacep.api.api.Api;
import com.viacep.api.model.Cep;
import com.viacep.api.repository.CepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.viacep.api.constant.CepConstant.*;
@Service
public class CepService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Api api;
    @Autowired
    private CepRepository repository;

    public Cep findCepApi(String cep) {
        return api.getCep(cep);
    }
    public Cep findCepDB(String cep) throws Exception {

       Optional<Cep> cepDB = repository.findByCep(cep);
       if (cepDB.isEmpty()) {
           Cep responseApi = findCepApi(cep);
           if (responseApi.getCep() == null) {
               logger.info(CEP_NOT_EXIST + cep);
               throw new Exception(CEP_NOT_EXIST);
           }

           logger.info(CEP_SEARCH + cep);
           logger.info(CEP_SAVED + cep);
           save(responseApi);
           return responseApi;
       }

        logger.info(CEP_DATABASE + cep);
        return cepDB.get();
    }

    public void findById(UUID id) throws Exception {
        Optional<Cep> cep =  repository.findById(id);
        if (cep.isEmpty()) {
            logger.info(CEP_NOT_EXIST);
            throw new Exception(CEP_NOT_EXIST);
        }
    }

    public void deleteAll() {
        repository.deleteAll();
    }
    public void deleteCepByUuid(UUID id) throws Exception {
        findById(id);
        repository.deleteById(id);
    }

    public void deleteCepByCep(String cep) throws Exception {
        Optional<Cep> cepDB = repository.findByCep(cep);
        if (cepDB.isEmpty()) {
            logger.info(CEP_NOT_EXIST + cep);
            throw new Exception(CEP_NOT_EXIST);
        }

        repository.deleteById(cepDB.get().getId());
    }

    public Object save(Cep cepModel) {
        return repository.save(cepModel);
    }
    public List<Cep> findAllCep() throws Exception {
        List<Cep> cep = repository.findAll();
        if (cep.isEmpty()) {
            logger.info(DATABASE_EMPTY);
            throw new Exception(DATABASE_EMPTY);
        }

        return cep;
    }
}
