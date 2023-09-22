package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CALCULO")
public class Calculo implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "CAL_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_CALCULO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_CALCULO", sequenceName = "SQ_CALCULO",allocationSize = 1)
    private Long id;
    @Column(name = "CAL_DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calData;
    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID", updatable = false, insertable = false)
    @ManyToOne
    private Loja loja;

    @JoinColumn(name = "SEM_ID_DE", referencedColumnName = "SEM_ID", updatable = false, insertable = false)
    @ManyToOne
    private Semana semanaDe;
    @JoinColumn(name = "SEM_ID_ATE", referencedColumnName = "SEM_ID", updatable = false, insertable = false)
    @ManyToOne
    private Semana semanaAte;

    @Mapper.CryptoRequired
    @Column(name = "SEM_ID_DE")
    private Long semIdDe;

    @Mapper.CryptoRequired
    @Column(name = "SEM_ID_ATE")
    private Long semIdAte;

    @Mapper.CryptoRequired
    @Column(name = "LOJ_ID")
    private Long lojId;

    @OneToMany(mappedBy = "calculo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<VendaProjetada> vendaProjetadaList;
    
    @OneToMany(mappedBy = "calculo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<GradeProjetada> gradeProjetadaList;
    
    @OneToMany(mappedBy = "calculo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<EstoquePrevisto> estoquePrevistoList;

    @OneToMany(mappedBy = "calculo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<GradePadrao> gradePadraoList;

    @OneToMany(mappedBy = "calculo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCalData() {
        return calData;
    }

    public void setCalData(Date calData) {
        this.calData = calData;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Semana getSemanaDe() {
        return semanaDe;
    }

    public void setSemanaDe(Semana semana) {
        this.semanaDe = semana;
    }

    public Semana getSemanaAte() {
        return semanaAte;
    }

    public void setSemanaAte(Semana semana1) {
        this.semanaAte = semana1;
    }

    public List<VendaProjetada> getVendaProjetadaList() {
        return vendaProjetadaList;
    }

    public void setVendaProjetadaList(List<VendaProjetada> vendaProjetadaList) {
        this.vendaProjetadaList = vendaProjetadaList;
    }

    public List<GradeProjetada> getGradeProjetadaList() {
        return gradeProjetadaList;
    }

    public void setGradeProjetadaList(List<GradeProjetada> gradeProjetadaList) {
        this.gradeProjetadaList = gradeProjetadaList;
    }

    public List<EstoquePrevisto> getEstoquePrevistoList() {
        return estoquePrevistoList;
    }

    public void setEstoquePrevistoList(List<EstoquePrevisto> estoquePrevistoList) {
        this.estoquePrevistoList = estoquePrevistoList;
    }

	public Long getSemIdDe() {
		return semIdDe;
	}

	public void setSemIdDe(Long semIdDe) {
		this.semIdDe = semIdDe;
	}

	public Long getSemIdAte() {
		return semIdAte;
	}

	public void setSemIdAte(Long semIdAte) {
		this.semIdAte = semIdAte;
	}

	public Long getLojId() {
		return lojId;
	}

	public void setLojId(Long lojId) {
		this.lojId = lojId;
	}
    
}
