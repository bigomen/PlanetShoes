package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "SAZONALIDADE")
public class Sazonalidade implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SAZ_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_SAZONALIDADE")
    @jakarta.persistence.SequenceGenerator(name = "SQ_SAZONALIDADE", sequenceName = "SQ_SAZONALIDADE",allocationSize = 1)
    @Mapper.CryptoRequired
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "SAZ_PORCENTAGEM_AJUSTE")
    private Double sazPorcentagemAjuste;
    
    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID", updatable = false, insertable = false)
    @ManyToOne(optional = false)
    private Loja loja;
    
    @JoinColumn(name = "SEM_ID", referencedColumnName = "SEM_ID", updatable = false, insertable = false)
    @ManyToOne(optional = false)
    private Semana semana;

    @Mapper.CryptoRequired
    @Column(name = "LOJ_ID")
    private Long lojId;

    @Mapper.CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

    @Column(name = "SAZ_PORCENTAGEM_ORIGINAL", updatable = false)
    private Double sazPorcentagemOriginal;


    public Long getLojId() {
        return lojId;
    }

    public void setLojId(Long lojId) {
        this.lojId = lojId;
    }

    public Long getSemId() {
        return semId;
    }

    public void setSemId(Long semId) {
        this.semId = semId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public Double getSazPorcentagemAjuste() {
		return sazPorcentagemAjuste;
	}

	public void setSazPorcentagemAjuste(Double porcentagemAjuste) {
		this.sazPorcentagemAjuste = porcentagemAjuste;
	}

    public Double getSazPorcentagemOriginal() {
        return sazPorcentagemOriginal;
    }

    public void setSazPorcentagemOriginal(Double sazPorcentagemOriginal) {
        this.sazPorcentagemOriginal = sazPorcentagemOriginal;
    }
}
