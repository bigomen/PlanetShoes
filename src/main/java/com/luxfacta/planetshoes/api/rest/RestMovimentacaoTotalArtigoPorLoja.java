package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;

public class RestMovimentacaoTotalArtigoPorLoja implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    private String artId;
    private String artDescricao;
    private String lojId;
    private String lojNome;
    private Long totalVendas;
    private Long totalEstoque;
    private Long totalAbastecimento;

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getArtDescricao() {
        return artDescricao;
    }

    public void setArtDescricao(String artDescricao) {
        this.artDescricao = artDescricao;
    }

    public Long getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Long totalVendas) {
        this.totalVendas = totalVendas;
    }

    public Long getTotalEstoque() {
        return totalEstoque;
    }

    public void setTotalEstoque(Long totalEstoque) {
        this.totalEstoque = totalEstoque;
    }

    public Long getTotalAbastecimento() {
        return totalAbastecimento;
    }

    public void setTotalAbastecimento(Long totalAbastecimento) {
        this.totalAbastecimento = totalAbastecimento;
    }

	public String getLojId() {
		return lojId;
	}

	public void setLojId(String lojId) {
		this.lojId = lojId;
	}

	public String getLojNome() {
		return lojNome;
	}

	public void setLojNome(String lojNome) {
		this.lojNome = lojNome;
	}
    
    
    
}
