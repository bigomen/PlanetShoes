package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "REGIAO")
@PageModel.SortField(fieldName = "regDescricao")
public class Regiao extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "REG_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_REGIAO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_REGIAO", sequenceName = "SQ_REGIAO",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "REG_DESCRICAO")
    private String regDescricao;
    
    @Column(name = "REG_TEMPO_ENTREGA")
    private Long regTempoEntrega;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "regiao")
    private List<Municipio> municipioList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegDescricao() {
        return regDescricao;
    }

    public void setRegDescricao(String regDescricao) {
        this.regDescricao = regDescricao;
    }

    public Long getRegTempoEntrega() {
        return regTempoEntrega;
    }

    public void setRegTempoEntrega(Long regTempoEntrega) {
        this.regTempoEntrega = regTempoEntrega;
    }

	public List<Municipio> getMunicipioList() {
		return municipioList;
	}

	public void setMunicipioList(List<Municipio> municipioList) {
		this.municipioList = municipioList;
	}

    
    
    
}
