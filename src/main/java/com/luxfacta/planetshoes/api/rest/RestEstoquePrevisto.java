package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.EstoquePrevisto;

import java.io.Serializable;

@Mapper.ReferenceModel(className = EstoquePrevisto.class)
public class RestEstoquePrevisto implements Serializable, IRestModel {

    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private Long espQuantidadePedidoFabrica;
    private Long espQuantidadeTransito;
    private Long espQuantidadeEstoqueLoja;
    private Long espQuantidadeRestEstoquePrevisto;
    private RestArtigo artigo;
    private RestCor cor;
    private Long numeracao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getEspQuantidadePedidoFabrica() {
        return espQuantidadePedidoFabrica;
    }

    public void setEspQuantidadePedidoFabrica(Long espQuantidadePedidoFabrica) {
        this.espQuantidadePedidoFabrica = espQuantidadePedidoFabrica;
    }

    public Long getEspQuantidadeTransito() {
        return espQuantidadeTransito;
    }

    public void setEspQuantidadeTransito(Long espQuantidadeTransito) {
        this.espQuantidadeTransito = espQuantidadeTransito;
    }

    public Long getEspQuantidadeEstoqueLoja() {
        return espQuantidadeEstoqueLoja;
    }

    public void setEspQuantidadeEstoqueLoja(Long espQuantidadeEstoqueLoja) {
        this.espQuantidadeEstoqueLoja = espQuantidadeEstoqueLoja;
    }

    public Long getEspQuantidadeRestEstoquePrevisto() {
        return espQuantidadeRestEstoquePrevisto;
    }

    public void setEspQuantidadeRestEstoquePrevisto(Long espQuantidadeRestEstoquePrevisto) {
        this.espQuantidadeRestEstoquePrevisto = espQuantidadeRestEstoquePrevisto;
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

    public Long getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Long numeracao) {
        this.numeracao = numeracao;
    }

    
}
