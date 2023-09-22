package com.luxfacta.planetshoes.api.model.kunden;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "VW_KUNDEN_NF_ITEM_SEMANA")
public class KundenNotaFiscalItemSemana implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "NFK_ID")
    private String id;
    

    @Column(name = "nfk_quantidade")
    private Long nfkQuantidade;
    

    @Column(name = "nfk_ean")
    private String nfkEan;


    @Column(name = "ljk_id")
    private Long ljkId;

    @Column(name = "COR_ID")
    private Long corId;

    @Column(name = "ART_ID")
    private Long artId;

    @Column(name = "LOJ_ID")
    private Long lojId;

    @Column(name = "ANC_ID")
    private Long ancId;

    @Column(name = "SEM_ID")
    private Long semId;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getNfkQuantidade() {
		return nfkQuantidade;
	}

	public void setNfkQuantidade(Long nfkQuantidade) {
		this.nfkQuantidade = nfkQuantidade;
	}

	public String getNfkEan() {
		return nfkEan;
	}

	public void setNfkEan(String nfkEan) {
		this.nfkEan = nfkEan;
	}

	public Long getLjkId() {
		return ljkId;
	}

	public void setLjkId(Long lojkId) {
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

	public Long getSemId() {
		return semId;
	}

	public void setSemId(Long semId) {
		this.semId = semId;
	}
    
    
}
