package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Telefone;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Telefone.class)
public class RestTelefone implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;

    private String telNumero;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelNumero() {
        return telNumero;
    }

    public void setTelNumero(String telNumero) {
        this.telNumero = telNumero;
    }


}
