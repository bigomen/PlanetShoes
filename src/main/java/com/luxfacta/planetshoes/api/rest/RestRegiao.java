package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Regiao;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Regiao.class)
public class RestRegiao implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Descrição")
    private String regDescricao;
    @NotNull(message = "Tempo Entrega")
    private Long regTempoEntrega;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegDescricao() {
        return regDescricao;
    }

    public void setRegDescricao(String regDescricao) {
        this.regDescricao = regDescricao;
    }

    public Long getRegTempoEntrega() {
        return regTempoEntrega;
    }

    public void setRegTempoEntrega(Long regTempoEntrega) {
        this.regTempoEntrega = regTempoEntrega;
    }

}
