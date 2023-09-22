package com.luxfacta.planetshoes.api.model.kunden;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "VW_KUNDEN_PEDIDOS_ABERTOS")
public class KundenPedidoAberto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "PDK_ID")
    private String id;

    @Column(name = "ljk_id")
    private Long ljkId;

    @Column(name = "pedk_saldo")
    private Long pedkSaldo;
    
    @Column(name = "pdk_faturado")
    private Long pdkFaturado;

    @Column(name = "pdk_solicitado")
    private Long pdkSolicitado;
    
    @Column(name = "pdk_reservado")
    private Long pdkReservado;

    @Column(name = "pdk_ean")
    private String pdkEan;

    @Column(name = "COR_ID")
    private Long corId;

    @Column(name = "ART_ID")
    private Long artId;

    @Column(name = "LOJ_ID")
    private Long lojId;

    @Column(name = "ANC_ID")
    private Long ancId;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getLjkId() {
		return ljkId;
	}

	public void setLjkId(Long ljkId) {
		this.ljkId = ljkId;
	}

	public Long getPedkSaldo() {
		return pedkSaldo;
	}

	public void setPedkSaldo(Long pedkSaldo) {
		this.pedkSaldo = pedkSaldo;
	}

	public Long getPdkFaturado() {
		return pdkFaturado;
	}

	public void setPdkFaturado(Long pdkFaturado) {
		this.pdkFaturado = pdkFaturado;
	}

	public Long getPdkSolicitado() {
		return pdkSolicitado;
	}

	public void setPdkSolicitado(Long pdkSolicitado) {
		this.pdkSolicitado = pdkSolicitado;
	}

	public Long getPdkReservado() {
		return pdkReservado;
	}

	public void setPdkReservado(Long pdkReservado) {
		this.pdkReservado = pdkReservado;
	}

	public String getPdkEan() {
		return pdkEan;
	}

	public void setPdkEan(String pdkEan) {
		this.pdkEan = pdkEan;
	}

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
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


	
	
    
}
