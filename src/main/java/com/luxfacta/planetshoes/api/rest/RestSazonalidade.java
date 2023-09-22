package com.luxfacta.planetshoes.api.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Sazonalidade;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Sazonalidade.class)
public class RestSazonalidade implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotNull(message = "Porcentagem Ajuste")
    private Double sazPorcentagemAjuste;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RestLoja loja;

    @NotNull(message = "Loja")
    private String lojId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RestSemana semana;

    @NotNull(message = "Semana")
    private String semId;

    private Double sazPorcentagemOriginal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getSazPorcentagemAjuste() {
        return sazPorcentagemAjuste;
    }

    public void setSazPorcentagemAjuste(Double sazPorcentagemAjuste) {
        this.sazPorcentagemAjuste = sazPorcentagemAjuste;
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

    public String getLojId() {
        return lojId;
    }

    public void setLojId(String lojId) {
        this.lojId = lojId;
    }

    public String getSemId() {
        return semId;
    }

    public void setSemId(String semId) {
        this.semId = semId;
    }

    public Double getSazPorcentagemOriginal() {
        return sazPorcentagemOriginal;
    }

    public void setSazPorcentagemOriginal(Double sazPorcentagemOriginal) {
        this.sazPorcentagemOriginal = sazPorcentagemOriginal;
    }
}
