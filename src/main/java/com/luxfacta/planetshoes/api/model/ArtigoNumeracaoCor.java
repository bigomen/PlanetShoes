package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ARTIGO_NUMERACAO_COR")
@PageModel.SortField(fieldName = "ancNumeracao")
public class ArtigoNumeracaoCor extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_ARTIGO_NUMERACAO_COR")
    @jakarta.persistence.SequenceGenerator(name = "SQ_ARTIGO_NUMERACAO_COR", sequenceName = "SQ_ARTIGO_NUMERACAO_COR",allocationSize = 1)
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "ANC_ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "ANC_NUMERACAO")
    private Integer ancNumeracao;

    @Basic(optional = true)
    @Column(name = "ANC_GRADE")
    private Integer ancGrade;

    
    @Column(name = "ANC_EAN")
    private String ancEan;

    @Column(name = "ANC_SKU")
    private String ancSku;
    
    @JoinColumn(name = "COR_ID", referencedColumnName = "COR_ID", updatable = false, insertable = false)
    @ManyToOne
    private Cor cor;

    @JoinColumn(name = "ART_ID", referencedColumnName = "ART_ID", updatable = false, insertable = false)
    @ManyToOne
    private Artigo artigo;
       
    @JoinColumn(name = "TAR_ID", referencedColumnName = "TAR_ID", updatable = false, insertable = false)
    @ManyToOne
    private TipoArtigo tipoArtigo;

    @Mapper.CryptoRequired
    @Column(name = "COR_ID")
    private Long corId;

    @Mapper.CryptoRequired
    @Column(name = "ART_ID")
    private Long artId;
    @Mapper.CryptoRequired
    @Column(name = "TAR_ID")
    private Long tarId;
    
    @OneToMany(mappedBy = "artigoNumeracaoCor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<GradeProjetada> gradeProjetadaList;
    
    @OneToMany(mappedBy = "artigoNumeracaoCor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<EstoquePrevisto> estoquePrevistoList;
    
    @OneToMany(mappedBy = "artigoNumeracaoCor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<SolicitacaoPedido> solicitacaoPedidoList;

    @OneToMany(mappedBy = "artigoNumeracaoCor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Movimentacao> movimentacaoList;

    @OneToMany(mappedBy = "artigoNumeracaoCor", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<PedidoArtigoNumeracaoCor> pedidoArtigoNumeracaoCorList;

    public List<Movimentacao> getMovimentacaoList() {
        return movimentacaoList;
    }

    public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
        this.movimentacaoList = movimentacaoList;
    }


    public TipoArtigo getTipoArtigo() {
        return tipoArtigo;
    }

    public void setTipoArtigo(TipoArtigo tipoArtigo) {
        this.tipoArtigo = tipoArtigo;
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

    public List<SolicitacaoPedido> getSolicitacaoPedidoList() {
        return solicitacaoPedidoList;
    }

    public void setSolicitacaoPedidoList(List<SolicitacaoPedido> solicitacaoPedidoList) {
        this.solicitacaoPedidoList = solicitacaoPedidoList;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Artigo getArtigo() {
		return artigo;
	}

	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}

	public Integer getAncNumeracao() {
		return ancNumeracao;
	}

	public void setAncNumeracao(Integer numeracao) {
		this.ancNumeracao = numeracao;
	}

	public String getAncEan() {
		return ancEan;
	}

	public void setAncEan(String ean) {
		this.ancEan = ean;
	}

	public String getAncSku() {
		return ancSku;
	}

	public void setAncSku(String sku) {
		this.ancSku = sku;
	}

	public Long getCorId() {
		return corId;
	}

	public void setCorId(Long corId) {
		this.corId = corId;
	}

	public Long getArtId() {
		return artId;
	}

	public void setArtId(Long artId) {
		this.artId = artId;
	}

	public Long getTarId() {
		return tarId;
	}

	public void setTarId(Long tarId) {
		this.tarId = tarId;
	}

    public List<PedidoArtigoNumeracaoCor> getPedidoArtigoNumeracaoCorList() {
        return pedidoArtigoNumeracaoCorList;
    }

    public void setPedidoArtigoNumeracaoCorList(List<PedidoArtigoNumeracaoCor> pedAnc) {
        this.pedidoArtigoNumeracaoCorList = pedAnc;
    }

	public Integer getAncGrade() {
		return ancGrade;
	}

	public void setAncGrade(Integer ancGrade) {
		this.ancGrade = ancGrade;
	}
    
    
    
}
