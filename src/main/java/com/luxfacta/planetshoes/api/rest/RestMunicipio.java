package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Municipio;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Municipio.class)
public class RestMunicipio implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private String munNome;
    private RestUf uf;
    private String ufId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMunNome() {
        return munNome;
    }

    public void setMunNome(String munNome) {
        this.munNome = munNome;
    }

    public RestUf getUf() {
        return uf;
    }

    public void setUf(RestUf uf) {
        this.uf = uf;
    }

    public String getUfId() {
        return ufId;
    }

    public void setUfId(String ufId) {
        this.ufId = ufId;
    }
}
