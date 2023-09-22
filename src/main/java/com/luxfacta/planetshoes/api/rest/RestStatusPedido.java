package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.StatusPedido;

import java.io.Serializable;

@Mapper.ReferenceModel(className = StatusPedido.class)
public class RestStatusPedido implements Serializable, IRestModel {

    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private String speDescricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpeDescricao() {
        return speDescricao;
    }

    public void setSpeDescricao(String speDescricao) {
        this.speDescricao = speDescricao;
    }


}
