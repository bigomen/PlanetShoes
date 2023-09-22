package com.luxfacta.planetshoes.api.model.views;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "VW_MOVIMENTACAO_TOTAL_LOJA_POR_FRANQUEADO_ARTIGO_COR")
public class MovimentacaoTotalLojaPorFranqueadoArtigoCor implements Serializable, IDatabaseModel {

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
    @Column(name = "COR_ID")
    private Long corId;

    @Column(name = "COR_DESCRICAO")
    private String corDescricao;

    @CryptoRequired
    @Column(name = "LOJ_ID")
    private Long lojId;

    @Column(name = "LOJ_NOME")
    private String lojNome;

    @CryptoRequired
    @Column(name = "FRQ_ID")
    private Long frqId;

    @Column(name = "FRQ_NOME")
    private String frqNome;
    
    @CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

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

    public Long getCorId() {
        return corId;
    }

    public void setCorId(Long corId) {
        this.corId = corId;
    }

    public String getCorDescricao() {
        return corDescricao;
    }

    public void setCorDescricao(String corDescricao) {
        this.corDescricao = corDescricao;
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
