package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "STATUS_PEDIDOS")
public class StatusPedido implements Serializable, IDatabaseModel {

    private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "SPE_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "SPE_DESCRICAO")
    private String speDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusPedidos")
    private List<Pedido> pedidosList;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeDescricao() {
        return speDescricao;
    }

    public void setSpeDescricao(String speDescricao) {
        this.speDescricao = speDescricao;
    }

    public List<Pedido> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedido> pedidosList) {
        this.pedidosList = pedidosList;
    }

    
}
