package com.viacep.api.repository;

import com.viacep.api.model.Cep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@EnableJpaRepositories
@Repository
public interface CepRepository extends JpaRepository<Cep, UUID> {
}
