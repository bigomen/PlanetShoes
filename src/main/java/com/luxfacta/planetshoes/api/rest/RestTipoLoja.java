package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.TipoLoja;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = TipoLoja.class)
public class RestTipoLoja implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Descricao")
    private String tloDescricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTloDescricao() {
        return tloDescricao;
    }

    public void setTloDescricao(String tloDescricao) {
        this.tloDescricao = tloDescricao;
    }

    
}
