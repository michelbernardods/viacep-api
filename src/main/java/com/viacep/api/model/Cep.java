package com.viacep.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

//@RedisHash(value = "ConsentOTP", timeToLive = 300)
//@AccessType(AccessType.Type.PROPERTY)

//@Data
@Entity
public class Cep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cep cep1 = (Cep) o;
        return Objects.equals(id, cep1.id) && Objects.equals(cep, cep1.cep) && Objects.equals(logradouro, cep1.logradouro) && Objects.equals(complemento, cep1.complemento) && Objects.equals(bairro, cep1.bairro) && Objects.equals(localidade, cep1.localidade) && Objects.equals(uf, cep1.uf) && Objects.equals(ibge, cep1.ibge) && Objects.equals(gia, cep1.gia) && Objects.equals(ddd, cep1.ddd) && Objects.equals(siafi, cep1.siafi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, logradouro, complemento, bairro, localidade, uf, ibge, gia, ddd, siafi);
    }

    @Override
    public String toString() {
        return "Cep{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}