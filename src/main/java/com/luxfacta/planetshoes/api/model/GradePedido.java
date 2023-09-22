package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "GRADE_PEDIDO")
public class GradePedido implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_GRADE_PEDIDO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_GRADE_PEDIDO", sequenceName = "SQ_GRADE_PEDIDO",allocationSize = 1)
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "GRD_ID")
    private Long id;

    @Column(name = "GRD_NUMERACAO")
    private Long grdNumeracao;

    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Loja loja;

    @Column(name = "LOJ_ID")
    private Long lojId;

    @Column(name = "GRD_DISTRIBUICAO")
    private Long grdDistribuicao;

    @Column(name = "GRD_PERCENTUAL")
    private Double grdPercentual;

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

    public Long getGrdNumeracao() {
        return grdNumeracao;
    }

    public void setGrdNumeracao(Long grdNumeracao) {
        this.grdNumeracao = grdNumeracao;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public Long getLojId() {
        return lojId;
    }

    public void setLojId(Long lojId) {
        this.lojId = lojId;
    }

    public Long getGrdDistribuicao() {
        return grdDistribuicao;
    }

    public void setGrdDistribuicao(Long grdDistribuicao) {
        this.grdDistribuicao = grdDistribuicao;
    }

    public Double getGrdPercentual() {
        return grdPercentual;
    }

    public void setGrdPercentual(Double grdPercentual) {
        this.grdPercentual = grdPercentual;
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
