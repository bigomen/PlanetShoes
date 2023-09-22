package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "GRADE_PROJETADA")
@PageModel.SortField(fieldName = "id")
public class GradeProjetada extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "GPR_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_GRADE_PROJETADA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_GRADE_PROJETADA", sequenceName = "SQ_GRADE_PROJETADA",allocationSize = 1)
    private Long id;
    @Column(name = "GPR_ESTOQUE_ANTES_ENTREGA")
    private Long gprEstoqueAntesEntrega;
    @Column(name = "GPR_VENDA_ANTES_ENTREGA")
    private Long gprVendaAntesEntrega;
    @Column(name = "GPR_DISTRIBUICAO_GRADE")
    private Double gprDistribuicaoGrade;
    @Column(name = "GPR_ESTOQUE_APOS_ENTREGA")
    private Long gprEstoqueAposEntrega;
    @Column(name = "GPR_VENDA_APOS_ENTREGA")
    private Long gprVendaAposEntrega;
    @Column(name = "GPR_PEDIDO_FABRICA")
    private Long gprPedidoFabrica;

    @Column(name = "GPR_PEDIDO_NOVO")
    private Long gprPedidoNovo;

    @Column(name = "GPR_PEDIDO_TRANSITO")
    private Long gprPedidoTransito;

    @Column(name = "GPR_ESTOQUE_ATUAL")
    private Long gprEstoqueAtual;

    
    @JoinColumn(name = "ANC_ID", referencedColumnName = "ANC_ID", insertable = false, updatable = false)
    @ManyToOne
    private ArtigoNumeracaoCor artigoNumeracaoCor;

    @Mapper.CryptoRequired
    @Column(name = "ANC_ID")
    private Long ancId;

    @Mapper.IgnoreRest
    @JoinColumn(name = "CAL_ID", referencedColumnName = "CAL_ID", updatable = false, insertable = false)
    @ManyToOne
    private Calculo calculo;

    @Mapper.IgnoreRest
    @Mapper.CryptoRequired
    @Column(name = "CAL_ID")
    private Long calId;


    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGprEstoqueAntesEntrega() {
        return gprEstoqueAntesEntrega;
    }

    public void setGprEstoqueAntesEntrega(Long gprQuantidadeEstoquePrevisto) {
        this.gprEstoqueAntesEntrega = gprQuantidadeEstoquePrevisto;
    }

    public Long getGprVendaAntesEntrega() {
        return gprVendaAntesEntrega;
    }

    public void setGprVendaAntesEntrega(Long gprQuantidadeVendaPrevista) {
        this.gprVendaAntesEntrega = gprQuantidadeVendaPrevista;
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

    public void setGprEstoqueAposEntrega(Long gprEstoqueProjetado) {
        this.gprEstoqueAposEntrega = gprEstoqueProjetado;
    }

    public Long getGprPedidoFabrica() {
        return gprPedidoFabrica;
    }

    public void setGprPedidoFabrica(Long gprQuantidadeCoberturaDesejada) {
        this.gprPedidoFabrica = gprQuantidadeCoberturaDesejada;
    }

    public ArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(ArtigoNumeracaoCor artigoNumeracaoCor) {
        this.artigoNumeracaoCor = artigoNumeracaoCor;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    public Long getAncId() {
        return ancId;
    }

    public void setAncId(Long ancId) {
        this.ancId = ancId;
    }

	public Long getGprVendaAposEntrega() {
		return gprVendaAposEntrega;
	}

	public void setGprVendaAposEntrega(Long gprVendaAposEntrega) {
		this.gprVendaAposEntrega = gprVendaAposEntrega;
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
