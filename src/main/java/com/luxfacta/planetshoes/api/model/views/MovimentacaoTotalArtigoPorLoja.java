package com.luxfacta.planetshoes.api.model.views;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VW_MOVIMENTACAO_TOTAL_ARTIGO_POR_LOJA")
public class MovimentacaoTotalArtigoPorLoja implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MTR_ID")
    private String id;

    @CryptoRequired
    @Column(name = "ART_ID")
    private Long artId;

    @Column(name = "ART_DESCRICAO")
    private String artDescricao;

    @CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;
    
    @CryptoRequired
    @Column(name = "LOJ_ID")
    private Long lojId;
   
    @Column(name = "LOJ_NOME")
    private String lojNome;

    
    @Column(name = "TOTAL_VENDAS")
    private Long totalVendas;

    @Column(name = "TOTAL_ESTOQUE")
    private Long totalEstoque;

    @Column(name = "TOTAL_ABASTECIMENTO")
    private Long totalAbastecimento;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public String getArtDescricao() {
        return artDescricao;
    }

    public void setArtDescricao(String artDescricao) {
        this.artDescricao = artDescricao;
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

	public Long getLojId() {
		return lojId;
	}

	public void setLojId(Long lojId) {
		this.lojId = lojId;
	}

	public String getLojNome() {
		return lojNome;
	}

	public void setLojNome(String lojNome) {
		this.lojNome = lojNome;
	}


   
}
