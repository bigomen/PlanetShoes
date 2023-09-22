package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.GradePedido;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = GradePedido.class)
public class RestGradePedido implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @RestId
    private String id;
    private Long grdNumeracao;
    private RestLoja loja;
    private String lojId;
    private Long grdDistribuicao;
    private Double grdPercentual;
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

    public Long getGrdNumeracao() {
        return grdNumeracao;
    }

    public void setGrdNumeracao(Long grdNumeracao) {
        this.grdNumeracao = grdNumeracao;
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

    public Long getGrdDistribuicao() {
        return grdDistribuicao;
    }

    public void setGrdDistribuicao(Long grdDistribuicao) {
        this.grdDistribuicao = grdDistribuicao;
    }

    public Double getGrdPercentual() {
        return grdPercentual;
    }

    public void setGrdPercentual(Double grdPercentual) {
        this.grdPercentual = grdPercentual;
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
