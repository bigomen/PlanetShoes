package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.MovimentacaoSazonalidade;


@Mapper.ReferenceModel(className = MovimentacaoSazonalidade.class)
public class RestMovimentacaoSazonalidade implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @RestId
    private String id;
    private Double msaFatorSazonalidade;
    private Double msaQuantidadeSazonalizada;
    private Double msaFatorPonderacao;
    private Long msaQuantidade;
    private Long msaEstoque;
    private Long msaEntrada;
    private String corId;
    private String artId;
    private RestSemana semana;
    private String semId;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMsaFatorSazonalidade() {
		return msaFatorSazonalidade;
	}
	public void setMsaFatorSazonalidade(Double msaFatorSazonalidade) {
		this.msaFatorSazonalidade = msaFatorSazonalidade;
	}
	public Double getMsaQuantidadeSazonalizada() {
		return msaQuantidadeSazonalizada;
	}
	public void setMsaQuantidadeSazonalizada(Double msaQuantidadeSazonalizada) {
		this.msaQuantidadeSazonalizada = msaQuantidadeSazonalizada;
	}
	public Double getMsaFatorPonderacao() {
		return msaFatorPonderacao;
	}
	public void setMsaFatorPonderacao(Double msaFatorPonderacao) {
		this.msaFatorPonderacao = msaFatorPonderacao;
	}
	public Long getMsaQuantidade() {
		return msaQuantidade;
	}
	public void setMsaQuantidade(Long msaQuantidade) {
		this.msaQuantidade = msaQuantidade;
	}
	public Long getMsaEstoque() {
		return msaEstoque;
	}
	public void setMsaEstoque(Long msaEstoque) {
		this.msaEstoque = msaEstoque;
	}
	public Long getMsaEntrada() {
		return msaEntrada;
	}
	public void setMsaEntrada(Long msaEntrada) {
		this.msaEntrada = msaEntrada;
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
	public RestSemana getSemana() {
		return semana;
	}
	public void setSemana(RestSemana semana) {
		this.semana = semana;
	}
	public String getSemId() {
		return semId;
	}
	public void setSemId(String semId) {
		this.semId = semId;
	}
	
    
    
}
