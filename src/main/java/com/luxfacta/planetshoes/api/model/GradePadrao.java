package com.luxfacta.planetshoes.api.model;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "GRADE_PADRAO")
@PageModel.SortField(fieldName = "grpNumeracao")
public class GradePadrao extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_GRADE_PADRAO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_GRADE_PADRAO", sequenceName = "SQ_GRADE_PADRAO",allocationSize = 1)
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "GRP_ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "GRP_NUMERACAO")
    private Integer grpNumeracao;
    

    @Basic(optional = false)
    @Column(name = "GRP_QUANTIDADE")
    private Long grpQuantidade;

    @Column(name = "GRP_PERCENTUAL")
    private Double grpPercentual;

    @Mapper.IgnoreRest
    @JoinColumn(name = "CAL_ID", referencedColumnName = "CAL_ID", insertable = false, updatable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Calculo calculo;

    @Mapper.IgnoreRest
    @Column(name = "CAL_ID")
    private Long calId;

    @JoinColumn(name = "COR_ID", referencedColumnName = "COR_ID", insertable = false, updatable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Cor cor;

    @Mapper.CryptoRequired
    @Column(name = "COR_ID")
    private Long corId;

    @JoinColumn(name = "ART_ID", referencedColumnName = "ART_ID", insertable = false, updatable = false)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Artigo artigo;

    @Mapper.CryptoRequired
    @Column(name = "ART_ID")
    private Long artId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrpNumeracao() {
        return grpNumeracao;
    }

    public void setGrpNumeracao(Integer grpNumeracao) {
        this.grpNumeracao = grpNumeracao;
    }

    public Long getGrpQuantidade() {
        return grpQuantidade;
    }

    public void setGrpQuantidade(Long grpQuantidade) {
        this.grpQuantidade = grpQuantidade;
    }

    public Double getGrpPercentual() {
        return grpPercentual;
    }

    public void setGrpPercentual(Double grpPercentual) {
        this.grpPercentual = grpPercentual;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Long getCorId() {
        return corId;
    }

    public void setCorId(Long corId) {
        this.corId = corId;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }
}
