package com.luxfacta.planetshoes.api.model.views;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "VW_MOVIMENTACAO_TOTAL_COR_POR_ARTIGO")
public class MovimentacaoTotalCorPorArtigo implements Serializable, IDatabaseModel {

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

    /*
    public static RestMovimentacaoTotalPorArtigoCor viewToRest(MovimentacaoTotalPorArtigoCor model) throws BusinessSecurityException {
        RestMovimentacaoTotalPorArtigoCor rest = new RestMovimentacaoTotalPorArtigoCor();
        rest.setArtId(Mapper.encryptId(model.getArtId()));
        rest.setArtDescricao(model.getArtDescricao());
        rest.setCorId(Mapper.encryptId(model.getCorId()));
        rest.setCorDescricao(model.getCorDescricao());
        rest.setTotalVendas(model.getTotalVendas());
        rest.setTotalEstoque(model.getTotalEstoque());
        rest.setTotalAbastecimento(model.getTotalAbastecimento());
        return rest;
    }
    */
    
}
