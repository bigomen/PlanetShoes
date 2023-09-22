package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Uf;

import java.io.Serializable;

@Mapper.ReferenceModel(className = Uf.class)
public class RestUf implements Serializable, IRestModel {

    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private String ufNome;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUfNome() {
        return ufNome;
    }

    public void setUfNome(String ufNome) {
        this.ufNome = ufNome;
    }

    
}
