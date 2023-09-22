package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;

public class RestMovimentacaoTotalFranqueadoPorArtigoCor implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    private String redId;
    private String frqId;
    private String frqNome;
    
    private String artId;
    private String artDescricao;
    private String corId;
    private String corDescricao;
    
    private String redDescricao;
    private Long totalVendas;
    private Long totalEstoque;
    private Long totalAbastecimento;

    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
    }

    public String getRedDescricao() {
        return redDescricao;
    }

    public void setRedDescricao(String redDescricao) {
        this.redDescricao = redDescricao;
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

	public String getFrqId() {
		return frqId;
	}

	public void setFrqId(String frqId) {
		this.frqId = frqId;
	}

	public String getFrqNome() {
		return frqNome;
	}

	public void setFrqNome(String frqNome) {
		this.frqNome = frqNome;
	}

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

	public String getCorId() {
		return corId;
	}

	public void setCorId(String corId) {
		this.corId = corId;
	}

	public String getCorDescricao() {
		return corDescricao;
	}

	public void setCorDescricao(String corDescricao) {
		this.corDescricao = corDescricao;
	}
    
    
    
}
