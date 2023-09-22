
package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "COR")
@PageModel.SortField(fieldName = "corDescricao")
public class Cor extends PageModel implements Serializable, IDatabaseModel {

    @Serial
	private static final long serialVersionUID = 1L;
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_COR")
    @jakarta.persistence.SequenceGenerator(name = "SQ_COR", sequenceName = "SQ_COR",allocationSize = 1)
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "COR_ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "COR_DESCRICAO")
    private String corDescricao;
    
    @Column(name = "COR_HEXA")
    private String corHexa;
    
    @Column(name = "COR_CODIGO_ERP")
    private Long corCodigoErp;
    
	@OneToMany(mappedBy = "cor", fetch = FetchType.LAZY)
    private List<MovimentacaoSazonalidade> movimentacaoSazonalidadeList;

    @OneToMany(mappedBy = "cor", fetch = FetchType.LAZY)
    private List<VendaProjetada> vendaProjetadaList;

    @OneToMany(mappedBy = "cor", fetch = FetchType.LAZY)
    private List<ArtigoNumeracaoCor> artigoNumeracaoCorList;

	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	public String getCorDescricao() {
		return corDescricao;
	}

	public void setCorDescricao(String descricao) {
		this.corDescricao = descricao;
	}

	public String getCorHexa() {
		return corHexa;
	}

	public void setCorHexa(String hexa) {
		this.corHexa = hexa;
	}

	public Long getCorCodigoErp() {
		return corCodigoErp;
	}

	public void setCorCodigoErp(Long codigoErp) {
		this.corCodigoErp = codigoErp;
	}


    
}
