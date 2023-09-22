package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.GradeProjetada;

@Mapper.ReferenceModel(className = GradeProjetada.class)
public class RestGradeProjetada implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @RestId
    private String id;
    private Long gprEstoqueAntesEntrega;
    private Long gprVendaAntesEntrega;
    private Double gprDistribuicaoGrade;
    private Long gprEstoqueAposEntrega;
    private Long gprVendaAposEntrega;
    private Long gprPedidoFabrica;
    private Long gprPedidoNovo;
    private Long gprPedidoTransito;
    private Long gprEstoqueAtual;

    
    private RestArtigoNumeracaoCor artigoNumeracaoCor;
    private String ancId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getGprEstoqueAntesEntrega() {
        return gprEstoqueAntesEntrega;
    }

    public void setGprEstoqueAntesEntrega(Long gprEstoqueAntesEntrega) {
        this.gprEstoqueAntesEntrega = gprEstoqueAntesEntrega;
    }

    public Long getGprVendaAntesEntrega() {
        return gprVendaAntesEntrega;
    }

    public void setGprVendaAntesEntrega(Long gprVendaAntesEntrega) {
        this.gprVendaAntesEntrega = gprVendaAntesEntrega;
    }

    public Double getGprDistribuicaoGrade() {
        return gprDistribuicaoGrade;
    }

    public void setGprDistribuicaoGrade(Double gprDistribuicaoGrade) {
        this.gprDistribuicaoGrade = gprDistribuicaoGrade;
    }

    public Long getGprEstoqueAposEntrega() {
        return gprEstoqueAposEntrega;
    }

    public void setGprEstoqueAposEntrega(Long gprEstoqueAposEntrega) {
        this.gprEstoqueAposEntrega = gprEstoqueAposEntrega;
    }

    public Long getGprVendaAposEntrega() {
        return gprVendaAposEntrega;
    }

    public void setGprVendaAposEntrega(Long gprVendaAposEntrega) {
        this.gprVendaAposEntrega = gprVendaAposEntrega;
    }

    public Long getGprPedidoFabrica() {
        return gprPedidoFabrica;
    }

    public void setGprPedidoFabrica(Long gprPedidoFabrica) {
        this.gprPedidoFabrica = gprPedidoFabrica;
    }

    public RestArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(RestArtigoNumeracaoCor artigoNumeracaoCor) {
        this.artigoNumeracaoCor = artigoNumeracaoCor;
    }

    public String getAncId() {
        return ancId;
    }

    public void setAncId(String ancId) {
        this.ancId = ancId;
    }

	public Long getGprPedidoNovo() {
		return gprPedidoNovo;
	}

	public void setGprPedidoNovo(Long gprPedidoNovo) {
		this.gprPedidoNovo = gprPedidoNovo;
	}

	public Long getGprPedidoTransito() {
		return gprPedidoTransito;
	}

	public void setGprPedidoTransito(Long gprPedidoTransito) {
		this.gprPedidoTransito = gprPedidoTransito;
	}

	public Long getGprEstoqueAtual() {
		return gprEstoqueAtual;
	}

	public void setGprEstoqueAtual(Long gprEstoqueAtual) {
		this.gprEstoqueAtual = gprEstoqueAtual;
	}
    
    
    
    
}
