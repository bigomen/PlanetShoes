package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Pedido;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Mapper.ReferenceModel(className = Pedido.class)
public class RestPedido implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotNull(message = "Data Pedido")
    private Date pedDataPedido;
    private Date pedDataFabricacao;
    private Date pedDataEstimativaEntrega;
    private Date pedDataEntrega;
    private Long pedIdRp;
    private RestLoja loja;
    private RestStatusPedido statusRestPedidos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public RestLoja getLoja() {
        return loja;
    }

    public void setLoja(RestLoja loja) {
        this.loja = loja;
    }

    public RestStatusPedido getStatusRestPedidos() {
        return statusRestPedidos;
    }

    public void setStatusRestPedidos(RestStatusPedido statusRestPedidos) {
        this.statusRestPedidos = statusRestPedidos;
    }


}
