package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SEMANA")
@PageModel.SortField(fieldName = "semNumero")
public class Semana extends PageModel implements Serializable, IDatabaseModel {

    @Serial
	private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "SEM_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_SEMANA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_SEMANA", sequenceName = "SQ_SEMANA",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "SEM_NUMERO")
    private Long semNumero;
    
    @Basic(optional = false)
    @Column(name = "SEM_ANO")
    private Long semAno;
    
    @Basic(optional = false)
    @Column(name = "SEM_DATA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date semDataInicio;
    
    @Basic(optional = false)
    @Column(name = "SEM_DATA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date semDataFim;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semana", fetch = FetchType.LAZY)
    private List<Sazonalidade> sazonalidadeList;
    
    @OneToMany(mappedBy = "semanaDe", fetch = FetchType.LAZY)
    private List<Calculo> calculoListDe;
    
    @OneToMany(mappedBy = "semanaAte", fetch = FetchType.LAZY)
    private List<Calculo> calculoListAte;
    
    @OneToMany(mappedBy = "semana", fetch = FetchType.LAZY)
    private List<VendaProjetada> vendaProjetadaList;
    
    @OneToMany(mappedBy = "semana", fetch = FetchType.LAZY)
    private List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList;
    
    @OneToMany(mappedBy = "semana", fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semana", fetch = FetchType.LAZY)
    private List<SolicitacaoPedido> solicitacaoPedidoList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getSemNumero() {
		return semNumero;
	}

	public void setSemNumero(Long numero) {
		this.semNumero = numero;
	}

	public Long getSemAno() {
		return semAno;
	}

	public void setSemAno(Long ano) {
		this.semAno = ano;
	}

	public Date getSemDataInicio() {
		return semDataInicio;
	}

	public void setSemDataInicio(Date dataInicio) {
		this.semDataInicio = dataInicio;
	}

	public Date getSemDataFim() {
		return semDataFim;
	}

	public void setSemDataFim(Date dataFim) {
		this.semDataFim = dataFim;
	}

	public List<Sazonalidade> getSazonalidadeList() {
		return sazonalidadeList;
	}

	public void setSazonalidadeList(List<Sazonalidade> sazonalidadeList) {
		this.sazonalidadeList = sazonalidadeList;
	}

	public List<Calculo> getCalculoListDe() {
		return calculoListDe;
	}

	public void setCalculoListDe(List<Calculo> calculoList) {
		this.calculoListDe = calculoList;
	}

	public List<Calculo> getCalculoListAte() {
		return calculoListAte;
	}

	public void setCalculoListAte(List<Calculo> calculoList1) {
		this.calculoListAte = calculoList1;
	}

	public List<VendaProjetada> getVendaProjetadaList() {
		return vendaProjetadaList;
	}

	public void setVendaProjetadaList(List<VendaProjetada> vendaProjetadaList) {
		this.vendaProjetadaList = vendaProjetadaList;
	}

	public List<MovimentacaoSazonalidade> getMovimentacaoSazonalidadeList() {
		return movimentacaoSazonalidadeList;
	}

	public void setMovimentacaoSazonalidadeList(List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList) {
		this.movimentacaoSazonalidadeList = movimentacaoSazonalidadeList;
	}

	public List<Movimentacao> getMovimentacaoList() {
		return movimentacaoList;
	}

	public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
		this.movimentacaoList = movimentacaoList;
	}

	public List<SolicitacaoPedido> getSolicitacaoPedidoList() {
		return solicitacaoPedidoList;
	}

	public void setSolicitacaoPedidoList(List<SolicitacaoPedido> solicitacaoPedidoList) {
		this.solicitacaoPedidoList = solicitacaoPedidoList;
	}


      
    
}
