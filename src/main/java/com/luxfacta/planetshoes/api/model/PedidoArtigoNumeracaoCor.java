package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PEDIDO_ARTIGO_NUMERACAO_COR")
public class PedidoArtigoNumeracaoCor implements Serializable, IDatabaseModel {

    private static final long serialVersionUID = 1L;

    
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_PEDIDO_ARTIGO_NUMERACAO_COR")
    @jakarta.persistence.SequenceGenerator(name = "SQ_PEDIDO_ARTIGO_NUMERACAO_COR", sequenceName = "SQ_PEDIDO_ARTIGO_NUMERACAO_COR",allocationSize = 1)
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "PAN_ID")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "PED_ID")
    private Pedido pedido;

    @Mapper.CryptoRequired
    @Column(name = "PED_ID", updatable = false, insertable = false)
    private Long pedId;

    @ManyToOne
    @JoinColumn(name = "ANC_ID")
    private ArtigoNumeracaoCor artigoNumeracaoCor;

    @Mapper.CryptoRequired
    @Column(name = "ANC_ID", updatable = false, insertable = false)
    private Long ancId;


    @Column(name = "PAN_QUANTIDADE")
    private Long panQuantidade;
    
    
    public Long getId() {
        return null;
    }

    public void setId(Long id) {

    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getPedId() {
        return pedId;
    }

    public void setPedId(Long pedidoId) {
        this.pedId = pedidoId;
    }

    public ArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(ArtigoNumeracaoCor anc) {
        this.artigoNumeracaoCor = anc;
    }

    public Long getAncId() {
        return ancId;
    }

    public void setAncId(Long ancId) {
        this.ancId = ancId;
    }

	public Long getPanQuantidade() {
		return panQuantidade;
	}

	public void setPanQuantidade(Long panQuantidade) {
		this.panQuantidade = panQuantidade;
	}
    
    
    
}
