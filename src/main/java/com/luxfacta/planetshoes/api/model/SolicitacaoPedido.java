package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "SOLICITACAO_PEDIDO")
@PageModel.SortField(fieldName = "id")
public class SolicitacaoPedido extends PageModel implements Serializable, IDatabaseModel {

    @Serial
	private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "SPE_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_SOLICITACAO_PEDIDO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_SOLICITACAO_PEDIDO", sequenceName = "SQ_SOLICITACAO_PEDIDO",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "SPE_QUANTIDADE")
    private long preQuantidade;
    
    @Column(name = "SPE_PEDIDO_ERP")
    private Long pedIdRp;
    
    @JoinColumn(name = "ANC_ID", referencedColumnName = "ANC_ID")
    @ManyToOne
    private ArtigoNumeracaoCor artigoNumeracaoCor;
    
    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID")
    @ManyToOne(optional = false)
    private Loja loja;
    
    @JoinColumn(name = "SEM_ID", referencedColumnName = "SEM_ID")
    @ManyToOne(optional = false)
    private Semana semana;
    
    @Column(name = "LOJ_ID", insertable = false, updatable = false)
    private Long lojId;
    
    @Column(name = "ANC_ID", insertable = false, updatable = false)
    private Long ancId;

    @Column(name = "SEM_ID", insertable = false, updatable = false)
    private Long semId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getPreQuantidade() {
		return preQuantidade;
	}

	public void setPreQuantidade(long quantidade) {
		this.preQuantidade = quantidade;
	}

	public Long getPedIdRp() {
		return pedIdRp;
	}

	public void setPedIdRp(Long pedidoErp) {
		this.pedIdRp = pedidoErp;
	}

	public ArtigoNumeracaoCor getArtigoNumeracaoCor() {
		return artigoNumeracaoCor;
	}

	public void setArtigoNumeracaoCor(ArtigoNumeracaoCor artigoNumeracaoCor) {
		this.artigoNumeracaoCor = artigoNumeracaoCor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public Long getLojId() {
		return lojId;
	}

	public void setLojId(Long lojId) {
		this.lojId = lojId;
	}

	public Long getAncId() {
		return ancId;
	}

	public void setAncId(Long ancId) {
		this.ancId = ancId;
	}

	public Long getSemId() {
		return semId;
	}

	public void setSemId(Long semId) {
		this.semId = semId;
	}

    
    
}
