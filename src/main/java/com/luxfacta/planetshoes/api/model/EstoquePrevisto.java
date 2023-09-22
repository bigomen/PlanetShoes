package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "ESTOQUE_PREVISTO")
@PageModel.SortField(fieldName = "id")
public class EstoquePrevisto extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_ESTOQUE_PREVISTO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_ESTOQUE_PREVISTO", sequenceName = "SQ_ESTOQUE_PREVISTO",allocationSize = 1)
    @Column(name = "ESP_ID")
    private Long id;
    @Column(name = "ESP_QUANTIDADE_PEDIDO_FABRICA")
    private Long espQuantidadePedidoFabrica;
    @Column(name = "ESP_QUANTIDADE_TRANSITO")
    private Long espQuantidadeTransito;
    @Column(name = "ESP_QUANTIDADE_ESTOQUE_LOJA")
    private Long espQuantidadeEstoqueLoja;
    @Column(name = "ESP_QUANTIDADE_ESTOQUE_PREVISTO")
    private Long espQuantidadeEstoquePrevisto;
    
    @ManyToOne
    @JoinColumn(name = "ANC_ID")
    private ArtigoNumeracaoCor artigoNumeracaoCor;
    
    @JoinColumn(name = "CAL_ID", referencedColumnName = "CAL_ID")
    @ManyToOne
    private Calculo calculo;

    @Column(name = "CAL_ID", insertable = false, updatable = false)
    private Long calId;

    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }
    
    
    public EstoquePrevisto() {
    }

    public EstoquePrevisto(Long espId) {
        this.id = espId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEspQuantidadePedidoFabrica() {
        return espQuantidadePedidoFabrica;
    }

    public void setEspQuantidadePedidoFabrica(Long espQuantidadePedidoFabrica) {
        this.espQuantidadePedidoFabrica = espQuantidadePedidoFabrica;
    }

    public Long getEspQuantidadeTransito() {
        return espQuantidadeTransito;
    }

    public void setEspQuantidadeTransito(Long espQuantidadeTransito) {
        this.espQuantidadeTransito = espQuantidadeTransito;
    }

    public Long getEspQuantidadeEstoqueLoja() {
        return espQuantidadeEstoqueLoja;
    }

    public void setEspQuantidadeEstoqueLoja(Long espQuantidadeEstoqueLoja) {
        this.espQuantidadeEstoqueLoja = espQuantidadeEstoqueLoja;
    }

    public Long getEspQuantidadeEstoquePrevisto() {
        return espQuantidadeEstoquePrevisto;
    }

    public void setEspQuantidadeEstoquePrevisto(Long espQuantidadeEstoquePrevisto) {
        this.espQuantidadeEstoquePrevisto = espQuantidadeEstoquePrevisto;
    }

    public ArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(ArtigoNumeracaoCor artigoNumeracaoCor) {
        this.artigoNumeracaoCor = artigoNumeracaoCor;
    }

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstoquePrevisto)) {
            return false;
        }
        EstoquePrevisto other = (EstoquePrevisto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luxfacta.planetshoes.api.model.EstoquePrevisto[ espId=" + id + " ]";
    }
    
}
