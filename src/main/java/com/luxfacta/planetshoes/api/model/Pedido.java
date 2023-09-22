package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
@PageModel.SortField(fieldName = "id")
public class Pedido extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PED_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO",allocationSize = 1)
    @Mapper.CryptoRequired
    private Long id;
    @Basic(optional = false)
    @Column(name = "PED_DATA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataPedido;
    @Column(name = "PED_DATA_FABRICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataFabricacao;
    @Column(name = "PED_DATA_ESTIMATIVA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataEstimativaEntrega;
    @Column(name = "PED_DATA_ENTREGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pedDataEntrega;
    @Column(name = "PED_ID_ERP")
    private Long pedIdRp;
    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID")
    @ManyToOne(optional = false)
    private Loja loja;
    @JoinColumn(name = "SPE_ID", referencedColumnName = "SPE_ID")
    @ManyToOne(optional = false)
    private StatusPedido statusPedidos;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<PedidoArtigoNumeracaoCor> pedAnc;

    @Column(name = "LOJ_ID", insertable = false, updatable = false)
    private Long lojId;

    public Long getLojId() {
        return lojId;
    }

    public void setLojId(Long lojId) {
        this.lojId = lojId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPedDataPedido() {
        return pedDataPedido;
    }

    public void setPedDataPedido(Date pedDataPedido) {
        this.pedDataPedido = pedDataPedido;
    }

    public Date getPedDataFabricacao() {
        return pedDataFabricacao;
    }

    public void setPedDataFabricacao(Date pedDataFabricacao) {
        this.pedDataFabricacao = pedDataFabricacao;
    }

    public Date getPedDataEstimativaEntrega() {
        return pedDataEstimativaEntrega;
    }

    public void setPedDataEstimativaEntrega(Date pedDataEstimativaEntrega) {
        this.pedDataEstimativaEntrega = pedDataEstimativaEntrega;
    }

    public Date getPedDataEntrega() {
        return pedDataEntrega;
    }

    public void setPedDataEntrega(Date pedDataEntrega) {
        this.pedDataEntrega = pedDataEntrega;
    }

    public Long getPedIdRp() {
        return pedIdRp;
    }

    public void setPedIdRp(Long pedIdRp) {
        this.pedIdRp = pedIdRp;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public StatusPedido getStatusPedidos() {
        return statusPedidos;
    }

    public void setStatusPedidos(StatusPedido statusPedidos) {
        this.statusPedidos = statusPedidos;
    }

    public List<PedidoArtigoNumeracaoCor> getPedAnc() {
        return pedAnc;
    }

    public void setPedAnc(List<PedidoArtigoNumeracaoCor> pedAnc) {
        this.pedAnc = pedAnc;
    }
}
