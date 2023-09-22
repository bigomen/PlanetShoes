package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.ArtigoNumeracaoCor;

import jakarta.validation.constraints.NotEmpty;

@Mapper.ReferenceModel(className = ArtigoNumeracaoCor.class)
public class RestArtigoNumeracaoCor implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private Integer ancNumeracao;
    private String ancEan;
    private String ancSku;
    private RestCor cor;
    private RestArtigo artigo;
    private RestTipoArtigo tipoArtigo;
    private String corId;
    private String artId;
    @NotEmpty(message = "Tipo artigo")
    private String tarId;
    private String ancFamilia;
    private Integer ancGrade;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAncNumeracao() {
        return ancNumeracao;
    }

    public void setAncNumeracao(Integer ancNumeracao) {
        this.ancNumeracao = ancNumeracao;
    }

    public String getAncEan() {
        return ancEan;
    }

    public void setAncEan(String ancEan) {
        this.ancEan = ancEan;
    }

    public String getAncSku() {
        return ancSku;
    }

    public void setAncSku(String ancSku) {
        this.ancSku = ancSku;
    }

    public RestCor getCor() {
        return cor;
    }

    public void setCor(RestCor cor) {
        this.cor = cor;
    }

    public RestArtigo getArtigo() {
        return artigo;
    }

    public void setArtigo(RestArtigo artigo) {
        this.artigo = artigo;
    }

    public RestTipoArtigo getTipoArtigo() {
        return tipoArtigo;
    }

    public void setTipoArtigo(RestTipoArtigo tipoArtigo) {
        this.tipoArtigo = tipoArtigo;
    }


    public String getCorId() {
        return corId;
    }

    public void setCorId(String corId) {
        this.corId = corId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getTarId() {
        return tarId;
    }

    public void setTarId(String tarId) {
        this.tarId = tarId;
    }

    public String getAncFamilia() {
        return ancFamilia;
    }

    public void setAncFamilia(String ancFamilia) {
        this.ancFamilia = ancFamilia;
    }

	public Integer getAncGrade() {
		return ancGrade;
	}

	public void setAncGrade(Integer ancGrade) {
		this.ancGrade = ancGrade;
	}
    
}
