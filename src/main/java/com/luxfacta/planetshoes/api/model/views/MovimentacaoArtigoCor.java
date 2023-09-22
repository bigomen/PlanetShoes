package com.luxfacta.planetshoes.api.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "VW_MOVIMENTACAO_ARTIGO_COR")
public class MovimentacaoArtigoCor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "MCT_ID")
    private String id;
    

    @Column(name = "COR_ID")
    private Long corId;


    @Column(name = "ART_ID")
    private Long artId;
    

    @Column(name = "SEM_ID")
    private Long semId;


    @Column(name = "LOJ_ID")
    private Long lojId;

    @Column(name = "MCT_QUANTIDADE")
    private Long mctQuantidade;

    @Column(name = "MCT_ESTOQUE")
    private Long mctEstoque;

    @Column(name = "MCT_ENTRADA")
    private Long mctEntrada;
    
    @Column(name = "MCT_QUANTIDADE_SAZONALIZADA")
    private Double mctQuantidadeSazonalizada;

    @Column(name = "MCT_FATOR_SAZONALIDADE")
    private Double mctFatorSazonalidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public Long getSemId() {
		return semId;
	}

	public void setSemId(Long semId) {
		this.semId = semId;
	}

	public Long getLojId() {
		return lojId;
	}

	public void setLojId(Long lojId) {
		this.lojId = lojId;
	}

	
	
	
	public Long getMctQuantidade() {
		return mctQuantidade;
	}

	public void setMctQuantidade(Long mctQuantidade) {
		this.mctQuantidade = mctQuantidade;
	}

	public Double getMctQuantidadeSazonalizada() {
		return mctQuantidadeSazonalizada;
	}

	public void setMctQuantidadeSazonalizada(Double mctQuantidadeSazonalizada) {
		this.mctQuantidadeSazonalizada = mctQuantidadeSazonalizada;
	}

	public Double getMctFatorSazonalidade() {
		return mctFatorSazonalidade;
	}

	public void setMctFatorSazonalidade(Double mctFatorSazonalidade) {
		this.mctFatorSazonalidade = mctFatorSazonalidade;
	}

	public Long getMctEstoque() {
		return mctEstoque;
	}

	public void setMctEstoque(Long mctEstoque) {
		this.mctEstoque = mctEstoque;
	}

	public Long getMctEntrada() {
		return mctEntrada;
	}

	public void setMctEntrada(Long mctEntrada) {
		this.mctEntrada = mctEntrada;
	}


    
    
}
