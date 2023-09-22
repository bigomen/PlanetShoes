package com.luxfacta.planetshoes.api.model.views;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VW_MOVIMENTACAO_TOTAL_FRANQUEADO")
public class MovimentacaoTotalFranqueado implements Serializable, IDatabaseModel  {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MTR_ID")
    private String id;

    @CryptoRequired
    @Column(name = "RED_ID")
    private Long redId;

    @Column(name = "RED_DESCRICAO")
    private String redDescricao;

    @CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

    @Column(name = "TOTAL_VENDAS")
    private Long totalVendas;

    @Column(name = "TOTAL_ESTOQUE")
    private Long totalEstoque;

    @Column(name = "TOTAL_ABASTECIMENTO")
    private Long totalAbastecimento;

    @CryptoRequired
    @Column(name = "FRQ_ID")
    private Long frqId;

    @Column(name = "FRQ_NOME")
    private String frqNome;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRedId() {
        return redId;
    }

    public void setRedId(Long redId) {
        this.redId = redId;
    }

    public String getRedDescricao() {
        return redDescricao;
    }

    public void setRedDescricao(String redDescricao) {
        this.redDescricao = redDescricao;
    }

    public Long getSemId() {
        return semId;
    }

    public void setSemId(Long semId) {
        this.semId = semId;
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

	public Long getFrqId() {
		return frqId;
	}

	public void setFrqId(Long frqId) {
		this.frqId = frqId;
	}

	public String getFrqNome() {
		return frqNome;
	}

	public void setFrqNome(String frqNome) {
		this.frqNome = frqNome;
	}

  
}
