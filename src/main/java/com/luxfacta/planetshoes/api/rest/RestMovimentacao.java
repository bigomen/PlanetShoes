package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Movimentacao;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Movimentacao.class)
public class RestMovimentacao implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private RestSemana semana;
    private RestArtigoNumeracaoCor artigoNumeracaoCor;
    private RestLoja loja;
    private String lojId;
    private String ancId;
    private String semId;
    private Long saida;
    private Long entrada;
    private Long saidaOutra;
    private Long entradaOutra;
    private Long estoqueInformado;
    private Long estoqueCalculado;
    private Short entroqueReinicia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAncId() {
        return ancId;
    }

    public void setAncId(String ancId) {
        this.ancId = ancId;
    }

    public String getSemId() {
        return semId;
    }

    public void setSemId(String semId) {
        this.semId = semId;
    }

    public Long getSaida() {
        return saida;
    }

    public void setSaida(Long saida) {
        this.saida = saida;
    }

    public Long getEntrada() {
        return entrada;
    }

    public void setEntrada(Long entrada) {
        this.entrada = entrada;
    }

    public Long getSaidaOutra() {
        return saidaOutra;
    }

    public void setSaidaOutra(Long saidaOutra) {
        this.saidaOutra = saidaOutra;
    }

    public Long getEntradaOutra() {
        return entradaOutra;
    }

    public void setEntradaOutra(Long entradaOutra) {
        this.entradaOutra = entradaOutra;
    }

    public Long getEstoqueInformado() {
        return estoqueInformado;
    }

    public void setEstoqueInformado(Long estoqueInformado) {
        this.estoqueInformado = estoqueInformado;
    }

    public Long getEstoqueCalculado() {
        return estoqueCalculado;
    }

    public void setEstoqueCalculado(Long estoqueCalculado) {
        this.estoqueCalculado = estoqueCalculado;
    }

    public Short getEntroqueReinicia() {
        return entroqueReinicia;
    }

    public void setEntroqueReinicia(Short entroqueReinicia) {
        this.entroqueReinicia = entroqueReinicia;
    }
}
