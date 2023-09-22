package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "LOJA")
@PageModel.SortField(fieldName = "lojNome")
public class Loja extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "LOJ_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_LOJA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_LOJA", sequenceName = "SQ_LOJA",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "LOJ_NOME")
    private String lojNome;
    
    @Basic(optional = false)
    @Column(name = "LOJ_CNPJ")
    private String lojCnpj;
    
    @Basic(optional = false)
    @Column(name = "LOJ_DATA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lojDataInicio;
    
    @Column(name = "LOJ_DATA_FIM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lojDataFim;

    @ManyToMany(mappedBy = "lojaList", fetch = FetchType.LAZY)
    private Set<Usuario> usuarioList;

    
    @OneToMany(mappedBy = "loja", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<Sazonalidade> sazonalidadeList;
    
    @OneToMany(mappedBy = "loja", fetch = FetchType.LAZY)
    private List<Movimentacao> movimentacaoList;
    
    @OneToMany(mappedBy = "loja", fetch = FetchType.LAZY)
    private List<Calculo> calculoList;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FRQ_ID", referencedColumnName = "FRQ_ID", updatable = false, insertable = false)
    private Franqueado franqueado;

    @Column(name = "FRQ_ID")
    @Mapper.CryptoRequired
    private Long frqId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "MUN_ID", referencedColumnName = "MUN_ID", updatable = false, insertable = false)
    private Municipio municipio;

    @Mapper.CryptoRequired
    @Column(name = "MUN_ID")
    private Long munId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "TLO_ID", referencedColumnName = "TLO_ID", updatable = false, insertable = false)
    private TipoLoja tipoLoja;

    @Mapper.CryptoRequired
    @Column(name = "TLO_ID")
    private Long tloId;
    
    @OneToMany(mappedBy = "loja", fetch = FetchType.LAZY)
    private List<SolicitacaoPedido> solicitacaoPedidoList;
    

    @OneToMany(mappedBy = "loja", fetch = FetchType.LAZY)
    private List<Pedido> pedidosList;

    @Column(name = "LOJ_CODIGO_INTERNO")
    private String lojCodigoInterno;
    
    @Column(name = "LOJ_QUANTIDADE_SEMANAS_ANALISE")
    private Integer lojQuantidadeSemanasAnalise;
   
    @Column(name = "LOJ_STATUS")
    private String lojStatus;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getLojDataInicio() {
        return lojDataInicio;
    }

    public void setLojDataInicio(Date lojDataInicio) {
        this.lojDataInicio = lojDataInicio;
    }

    public Date getLojDataFim() {
        return lojDataFim;
    }

    public void setLojDataFim(Date lojDataFim) {
        this.lojDataFim = lojDataFim;
    }

    public Set<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(Set<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }


    public List<Sazonalidade> getSazonalidadeList() {
        return sazonalidadeList;
    }

    public void setSazonalidadeList(List<Sazonalidade> sazonalidadeList) {
        this.sazonalidadeList = sazonalidadeList;
    }


    public List<Movimentacao> getMovimentacaoList() {
        return movimentacaoList;
    }

    public void setMovimentacaoList(List<Movimentacao> movimentacaoList) {
        this.movimentacaoList = movimentacaoList;
    }

    public List<Calculo> getCalculoList() {
        return calculoList;
    }

    public void setCalculoList(List<Calculo> calculoList) {
        this.calculoList = calculoList;
    }


    public Franqueado getFranqueado() {
		return franqueado;
	}

	public void setFranqueado(Franqueado franqueado) {
		this.franqueado = franqueado;
	}

	public Long getFrqId() {
		return frqId;
	}

	public void setFrqId(Long frqId) {
		this.frqId = frqId;
	}

	public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public TipoLoja getTipoLoja() {
        return tipoLoja;
    }

    public void setTipoLoja(TipoLoja tipoLoja) {
        this.tipoLoja = tipoLoja;
    }

    public List<SolicitacaoPedido> getSolicitacaoPedidoList() {
        return solicitacaoPedidoList;
    }

    public void setSolicitacaoPedidoList(List<SolicitacaoPedido> solicitacaoPedidoList) {
        this.solicitacaoPedidoList = solicitacaoPedidoList;
    }

    public List<Pedido> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedido> pedidosList) {
        this.pedidosList = pedidosList;
    }

    
    public void addUsuario(Usuario u) {
		usuarioList.add(u);
		u.getLojaList().add(this);
	}

	public void removeUsuario(Usuario u) {
		usuarioList.remove(u);
		u.getLojaList().remove(this);
	}

	public String getLojNome() {
		return lojNome;
	}

	public void setLojNome(String nome) {
		this.lojNome = nome;
	}

	public String getLojCnpj() {
		return lojCnpj;
	}

	public void setLojCnpj(String cnpj) {
		this.lojCnpj = cnpj;
	}

    public Long getMunId() {
        return munId;
    }

    public void setMunId(Long munId) {
        this.munId = munId;
    }

    public Long getTloId() {
        return tloId;
    }

    public void setTloId(Long tipoLojaId) {
        this.tloId = tipoLojaId;
    }

	public String getLojCodigoInterno() {
		return lojCodigoInterno;
	}

	public void setLojCodigoInterno(String lojCodigoInterno) {
		this.lojCodigoInterno = lojCodigoInterno;
	}

	public Integer getLojQuantidadeSemanasAnalise() {
		return lojQuantidadeSemanasAnalise;
	}

	public void setLojQuantidadeSemanasAnalise(Integer lojQuantidadeSemanasAnalise) {
		this.lojQuantidadeSemanasAnalise = lojQuantidadeSemanasAnalise;
	}

	public String getLojStatus() {
		return lojStatus;
	}

	public void setLojStatus(String lojStatus) {
		this.lojStatus = lojStatus;
	}
    
    
    
}
