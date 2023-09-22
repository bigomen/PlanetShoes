package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "FRANQUEADO")
@PageModel.SortField(fieldName = "frqNome")
public class Franqueado extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "FRQ_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_FRANQUEADO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_FRANQUEADO", sequenceName = "SQ_FRANQUEADO",allocationSize = 1)
    private Long id;

    @Column(name = "FRQ_NOME")
    private String frqNome;

    @Column(name = "FRQ_CODIGO")
    private String frqCodigo;

    @OneToMany(mappedBy = "franqueado", fetch = FetchType.LAZY)
    private List<Loja> lojaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RED_ID", referencedColumnName = "RED_ID", updatable = false, insertable = false)
    private Rede rede;

    @Column(name = "RED_ID")
    @Mapper.CryptoRequired
    private Long redId;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Loja> getLojaList() {
        return lojaList;
    }

    public void setLojaList(List<Loja> lojaList) {
        this.lojaList = lojaList;
    }

	public String getFrqNome() {
		return frqNome;
	}

	public void setFrqNome(String frqNome) {
		this.frqNome = frqNome;
	}
	
	public String getFrqCodigo() {
		return frqCodigo;
	}

	public void setFrqCodigo(String frqCodigo) {
		this.frqCodigo = frqCodigo;
	}

	public Rede getRede() {
		return rede;
	}

	public void setRede(Rede rede) {
		this.rede = rede;
	}

	public Long getRedId() {
		return redId;
	}

	public void setRedId(Long redId) {
		this.redId = redId;
	}
    
    
    
}
