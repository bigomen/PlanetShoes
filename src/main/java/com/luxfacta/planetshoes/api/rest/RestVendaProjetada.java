package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.VendaProjetada;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = VendaProjetada.class)
public class RestVendaProjetada implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private Double vepFatorSazonalidade;
    private Double vepQuantidade;
    private RestArtigo artigo;
    private RestCor cor;
    private RestSemana semana;
    private RestCalculo calculo;
    private String calId;
    private String semId;
    private String corId;
    private String artId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getVepFatorSazonalidade() {
        return vepFatorSazonalidade;
    }

    public void setVepFatorSazonalidade(Double vepFatorSazonalidade) {
        this.vepFatorSazonalidade = vepFatorSazonalidade;
    }

    public Double getVepQuantidade() {
        return vepQuantidade;
    }

    public void setVepQuantidade(Double vepQuantidade) {
        this.vepQuantidade = vepQuantidade;
    }

    public RestArtigo getArtigo() {
        return artigo;
    }

    public void setArtigo(RestArtigo artigo) {
        this.artigo = artigo;
    }

    public RestCor getCor() {
        return cor;
    }

    public void setCor(RestCor cor) {
        this.cor = cor;
    }

    public RestSemana getSemana() {
        return semana;
    }

    public void setSemana(RestSemana semana) {
        this.semana = semana;
    }

    public RestCalculo getCalculo() {
        return calculo;
    }

    public void setCalculo(RestCalculo calculo) {
        this.calculo = calculo;
    }

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }

    public String getSemId() {
        return semId;
    }

    public void setSemId(String semId) {
        this.semId = semId;
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
}
