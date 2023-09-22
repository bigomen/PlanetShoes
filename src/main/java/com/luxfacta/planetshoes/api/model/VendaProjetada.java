package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "VENDA_PROJETADA")
@PageModel.SortField(fieldName = "id")
public class VendaProjetada extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VEP_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_VENDA_PROJETADA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_VENDA_PROJETADA", sequenceName = "SQ_VENDA_PROJETADA",allocationSize = 1)
    @Mapper.CryptoRequired
    private Long id;

    @Column(name = "VEP_FATOR_SAZONALIDADE")
    private Double vepFatorSazonalidade;

    @Column(name = "VEP_QUANTIDADE")
    private Double vepQuantidade;

    @Mapper.IgnoreRest
    @JoinColumn(name = "CAL_ID", referencedColumnName = "CAL_ID", insertable = false, updatable = false)
    @ManyToOne
    private Calculo calculo;

    @Mapper.IgnoreRest
    @Mapper.CryptoRequired
    @Column(name = "CAL_ID")
    private Long calId;
    
    @JoinColumn(name = "SEM_ID", referencedColumnName = "SEM_ID", insertable = false, updatable = false)
    @ManyToOne
    private Semana semana;

    @Mapper.CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

    @JoinColumn(name = "COR_ID", referencedColumnName = "COR_ID", insertable = false, updatable = false)
    @ManyToOne
    private Cor cor;

    @Mapper.CryptoRequired
    @Column(name = "COR_ID")
    private Long corId;

    @JoinColumn(name = "ART_ID", referencedColumnName = "ART_ID", insertable = false, updatable = false)
    @ManyToOne
    private Artigo artigo;

    @Mapper.CryptoRequired
    @Column(name = "ART_ID")
    private Long artId;

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

    public Double getVepFatorSazonalidade() {
        return vepFatorSazonalidade;
    }

    public void setVepFatorSazonalidade(Double vepFatorSazonalidade) {
        this.vepFatorSazonalidade = vepFatorSazonalidade;
    }

    public Double getVepQuantidade() {
        return vepQuantidade;
    }

    public void setVepQuantidade(Double vepQuantidade) {
        this.vepQuantidade = vepQuantidade;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

    public Long getSemId() {
        return semId;
    }

    public void setSemId(Long semId) {
        this.semId = semId;
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
}
