package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Artigo;
import com.luxfacta.planetshoes.api.model.ArtigoNumeracaoCor;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Mapper.ReferenceModel(className = Artigo.class)
public class RestArtigo implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Descricao")
    private String artDescricao;
    @NotEmpty(message = "Fabricado")
    private Short artFabricado;
    private RestFamilia familia;
    @NotEmpty(message = "Familia")
    private String famId;
    private List<RestMovimentacaoSazonalidade> movimentacaoSazonalidadeList;
    private List<RestVendaProjetada> vendaProjetadaList;
    private List<ArtigoNumeracaoCor> artigoNumeracaoCorList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtDescricao() {
        return artDescricao;
    }

    public void setArtDescricao(String artDescricao) {
        this.artDescricao = artDescricao;
    }

    public Short getArtFabricado() {
        return artFabricado;
    }

    public void setArtFabricado(Short artFabricado) {
        this.artFabricado = artFabricado;
    }

    public List<RestMovimentacaoSazonalidade> getMovimentacaoSazonalidadeList() {
        return movimentacaoSazonalidadeList;
    }

    public void setMovimentacaoSazonalidadeList(List<RestMovimentacaoSazonalidade> movimentacaoSazonalidadeList) {
        this.movimentacaoSazonalidadeList = movimentacaoSazonalidadeList;
    }

    public List<RestVendaProjetada> getVendaProjetadaList() {
        return vendaProjetadaList;
    }

    public void setVendaProjetadaList(List<RestVendaProjetada> vendaProjetadaList) {
        this.vendaProjetadaList = vendaProjetadaList;
    }

    public String getFamId() {
        return famId;
    }

    public void setFamId(String famId) {
        this.famId = famId;
    }

    public List<ArtigoNumeracaoCor> getArtigoNumeracaoCorList() {
        return artigoNumeracaoCorList;
    }

    public void setArtigoNumeracaoCorList(List<ArtigoNumeracaoCor> artigoNumeracaoCorList) {
        this.artigoNumeracaoCorList = artigoNumeracaoCorList;
    }

    public RestFamilia getFamilia() {
        return familia;
    }

    public void setFamilia(RestFamilia familia) {
        this.familia = familia;
    }
}
