package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "REDE")
@PageModel.SortField(fieldName = "redDescricao")
public class Rede extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "RED_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_REDE")
    @jakarta.persistence.SequenceGenerator(name = "SQ_REDE", sequenceName = "SQ_REDE",allocationSize = 1)
    private Long id;

    @Column(name = "RED_DESCRICAO")
    private String redDescricao;

    @OneToMany(mappedBy = "rede", fetch = FetchType.LAZY)
    private List<Franqueado> franqueadoList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRedDescricao() {
        return redDescricao;
    }

    public void setRedDescricao(String redDescricao) {
        this.redDescricao = redDescricao;
    }

	public List<Franqueado> getFranqueadoList() {
		return franqueadoList;
	}

	public void setFranqueadoList(List<Franqueado> franqueadoList) {
		this.franqueadoList = franqueadoList;
	}


    

}
