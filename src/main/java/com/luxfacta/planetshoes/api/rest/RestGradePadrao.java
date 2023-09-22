/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.GradePadrao;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = GradePadrao.class)
public class RestGradePadrao implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @RestId
    private String id;
    private Long grpNumeracao;
    private RestLoja loja;
    private String lojId;
    private Long grpQuantidade;
    private Double grpPercentual;
    private RestCor cor;
    private String corId;
    private RestArtigo artigo;
    private String artId;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGrpNumeracao() {
        return grpNumeracao;
    }

    public void setGrpNumeracao(Long grpNumeracao) {
        this.grpNumeracao = grpNumeracao;
    }

    public RestLoja getLoja() {
        return loja;
    }

    public void setLoja(RestLoja loja) {
        this.loja = loja;
    }

    public String getLojId() {
        return lojId;
    }

    public void setLojId(String lojId) {
        this.lojId = lojId;
    }

    public Long getGrpQuantidade() {
        return grpQuantidade;
    }

    public void setGrpQuantidade(Long grpQuantidade) {
        this.grpQuantidade = grpQuantidade;
    }

    public Double getGrpPercentual() {
        return grpPercentual;
    }

    public void setGrpPercentual(Double grpPercentual) {
        this.grpPercentual = grpPercentual;
    }

    public RestCor getCor() {
        return cor;
    }

    public void setCor(RestCor cor) {
        this.cor = cor;
    }

    public String getCorId() {
        return corId;
    }

    public void setCorId(String corId) {
        this.corId = corId;
    }

    public RestArtigo getArtigo() {
        return artigo;
    }

    public void setArtigo(RestArtigo artigo) {
        this.artigo = artigo;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }
}
