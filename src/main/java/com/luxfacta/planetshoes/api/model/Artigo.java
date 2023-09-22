package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ARTIGO")
@PageModel.SortField(fieldName = "artDescricao")
public class Artigo extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_ARTIGO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_ARTIGO", sequenceName = "SQ_ARTIGO",allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "ART_ID")
    @Mapper.CryptoRequired
    private Long id;

    @Basic(optional = false)
    @Column(name = "ART_DESCRICAO")
    private String artDescricao;
    
    @Column(name = "ART_FABRICADO")
    private Short artFabricado;
    
    @JoinColumn(name = "FAM_ID", referencedColumnName = "FAM_ID", updatable = false, insertable = false)
    @ManyToOne(optional = false)
    private Familia familia;

    @Mapper.CryptoRequired
    @Column(name = "FAM_ID")
    private Long famId;
    
    @OneToMany(mappedBy = "artigo", fetch = FetchType.LAZY)
    private List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList;

    @OneToMany(mappedBy = "artigo", fetch = FetchType.LAZY)
    private List<VendaProjetada> vendaProjetadaList;

    @OneToMany(mappedBy = "artigo", fetch = FetchType.LAZY)
    private List<ArtigoNumeracaoCor> artigoNumeracaoCorList;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getArtDescricao() {
		return artDescricao;
	}

	public void setArtDescricao(String descricao) {
		this.artDescricao = descricao;
	}

	public Short getArtFabricado() {
		return artFabricado;
	}

	public void setArtFabricado(Short fabricado) {
		this.artFabricado = fabricado;
	}

	public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

	public List<MovimentacaoSazonalidade> getMovimentacaoSazonalidadeList() {
		return movimentacaoSazonalidadeList;
	}

	public void setMovimentacaoSazonalidadeList(List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList) {
		this.movimentacaoSazonalidadeList = movimentacaoSazonalidadeList;
	}

	public List<VendaProjetada> getVendaProjetadaList() {
		return vendaProjetadaList;
	}

	public void setVendaProjetadaList(List<VendaProjetada> vendaProjetadaList) {
		this.vendaProjetadaList = vendaProjetadaList;
	}

	public List<ArtigoNumeracaoCor> getArtigoNumeracaoCorList() {
		return artigoNumeracaoCorList;
	}

	public void setArtigoNumeracaoCorList(List<ArtigoNumeracaoCor> artigoNumeracaoCorList) {
		this.artigoNumeracaoCorList = artigoNumeracaoCorList;
	}

    public Long getFamId() {
        return famId;
    }

    public void setFamId(Long famId) {
        this.famId = famId;
    }
}
