
package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Cor;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Mapper.ReferenceModel(className = Cor.class)
public class RestCor implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Descrição")
    private String corDescricao;
    private String corHexa;
    private Long corCodigoErp;
    private List<RestMovimentacaoSazonalidade> movimentacaoSazonalidadeList;
    private List<RestVendaProjetada> vendaProjetadaList;
    private List<RestArtigoNumeracaoCor> artigoNumeracaoCorList;

    public RestCor() {
    }

    public RestCor(String corId) {
        this.id = corId;
    }

    public RestCor(String corId, String corDescricao) {
        this.id = corId;
        this.corDescricao = corDescricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorDescricao() {
        return corDescricao;
    }

    public void setCorDescricao(String corDescricao) {
        this.corDescricao = corDescricao;
    }

    public String getCorHexa() {
        return corHexa;
    }

    public void setCorHexa(String corHexa) {
        this.corHexa = corHexa;
    }

    public Long getCorCodigoErp() {
        return corCodigoErp;
    }

    public void setCorCodigoErp(Long corCodigoErp) {
        this.corCodigoErp = corCodigoErp;
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

    public List<RestArtigoNumeracaoCor> getArtigoNumeracaoCorList() {
        return artigoNumeracaoCorList;
    }

    public void setArtigoNumeracaoCorList(List<RestArtigoNumeracaoCor> artigoNumeracaoCorList) {
        this.artigoNumeracaoCorList = artigoNumeracaoCorList;
    }
}
