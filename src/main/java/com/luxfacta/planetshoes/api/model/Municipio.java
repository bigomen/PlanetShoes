package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MUNICIPIO")
public class Municipio implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MUN_ID")
    @Mapper.CryptoRequired
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_MUNICIPIO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_MUNICIPIO", sequenceName = "SQ_MUNICIPIO",allocationSize = 1)
    private Long id;
    @Basic(optional = false)
    @Column(name = "MUN_NOME")
    private String munNome;

    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municipio")
    private List<Loja> lojaList;


    @JoinColumn(name = "REG_ID", referencedColumnName = "REG_ID", insertable=false, updatable=false)
    @ManyToOne(optional = true)
    private Regiao regiao;
    
    @JoinColumn(name = "UF_ID", referencedColumnName = "UF_ID", insertable=false, updatable=false)
    @ManyToOne(optional = false)
    private Uf uf;
    
    @Mapper.CryptoRequired
    @Column(name = "UF_ID")
    private Long ufId;

    @Mapper.CryptoRequired
    @Column(name = "REG_ID")
    private Long regId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunNome() {
        return munNome;
    }

    public void setMunNome(String munNome) {
        this.munNome = munNome;
    }


    public List<Loja> getLojaList() {
        return lojaList;
    }

    public void setLojaList(List<Loja> lojaList) {
        this.lojaList = lojaList;
    }

    public Uf getUf() {
        return uf;
    }

    public void setUf(Uf uf) {
        this.uf = uf;
    }

    public Long getUfId() {
        return ufId;
    }

    public void setUfId(Long ufId) {
        this.ufId = ufId;
    }

	public Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Long getRegId() {
		return regId;
	}

	public void setRegId(Long regId) {
		this.regId = regId;
	}

    

    
}
