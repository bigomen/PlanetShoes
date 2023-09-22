package com.luxfacta.planetshoes.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.SolicitacaoPedido;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = SolicitacaoPedido.class)
public class RestSolicitacaoPedido implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotNull(message = "Quantidade")
    private long preQuantidade;
    private Long pedIdRp;
    @Mapper.RestNotNull
    private RestArtigoNumeracaoCor artigoNumeracaoCor;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RestLoja loja;
    @Mapper.RestNotNull
    private RestSemana semana;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getPreQuantidade() {
        return preQuantidade;
    }

    public void setPreQuantidade(long preQuantidade) {
        this.preQuantidade = preQuantidade;
    }

    public Long getPedIdRp() {
        return pedIdRp;
    }

    public void setPedIdRp(Long pedIdRp) {
        this.pedIdRp = pedIdRp;
    }

    public RestLoja getLoja() {
        return loja;
    }

    public void setLoja(RestLoja loja) {
        this.loja = loja;
    }

    public RestSemana getSemana() {
        return semana;
    }

    public void setSemana(RestSemana semana) {
        this.semana = semana;
    }

    public RestArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(RestArtigoNumeracaoCor artigoNumeracaoCor) {
        this.artigoNumeracaoCor = artigoNumeracaoCor;
    }
}
