package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "FAMILIA")
@PageModel.SortField(fieldName = "famMarca")
public class Familia extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_FAMILIA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_FAMILIA", sequenceName = "SQ_FAMILIA",allocationSize = 1)
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "FAM_ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "FAM_MARCA")
    private String famMarca;

    @Mapper.BooleanToString
    @Column(name = "FAM_GERA_PEDIDO")
    private String famGeraPedido;
    
    @Column(name = "FAM_QUANTIDADE_SEMANAS_ANALISE")
    private Integer famQuantidadeSemanasAnalise;
    
    @Column(name = "FAM_QUANTIDADE_SEMANAS_PREVISAO")
    private Integer famQuantidadeSemanasPrevisao;

    @Column(name = "FAM_QUANTIDADE_SEMANAS_GRADE")
    private Integer famQuantidadeSemanasGrade;

    @Column(name = "FAM_QUANTIDADE_SEMANAS_COBERTURA")
    private Integer famQuantidadeSemanasCobertura;

    @Column(name = "FAM_PONDERACAO")
    private String famPonderacao;
    
    
    @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
    private List<Artigo> artigoList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getFamGeraPedido() {
		return famGeraPedido;
	}

	public void setFamGeraPedido(String famGeraPedido) {
		this.famGeraPedido = famGeraPedido;
	}

	public String getFamMarca() {
		return famMarca;
	}

	public void setFamMarca(String marca) {
		this.famMarca = marca;
	}

	
    public List<Artigo> getArtigoList() {
        return artigoList;
    }

    public void setArtigoList(List<Artigo> artigoList) {
        this.artigoList = artigoList;
    }

 

	public String getFamPonderacao() {
		return famPonderacao;
	}

	public void setFamPonderacao(String famPonderacao) {
		this.famPonderacao = famPonderacao;
	}

	public Integer getFamQuantidadeSemanasAnalise() {
		return famQuantidadeSemanasAnalise;
	}

	public void setFamQuantidadeSemanasAnalise(Integer famQuantidadeSemanasAnalise) {
		this.famQuantidadeSemanasAnalise = famQuantidadeSemanasAnalise;
	}

	public Integer getFamQuantidadeSemanasPrevisao() {
		return famQuantidadeSemanasPrevisao;
	}

	public void setFamQuantidadeSemanasPrevisao(Integer famQuantidadeSemanasPrevisao) {
		this.famQuantidadeSemanasPrevisao = famQuantidadeSemanasPrevisao;
	}

	public Integer getFamQuantidadeSemanasGrade() {
		return famQuantidadeSemanasGrade;
	}

	public void setFamQuantidadeSemanasGrade(Integer famQuantidadeSemanasGrade) {
		this.famQuantidadeSemanasGrade = famQuantidadeSemanasGrade;
	}

	public Integer getFamQuantidadeSemanasCobertura() {
		return famQuantidadeSemanasCobertura;
	}

	public void setFamQuantidadeSemanasCobertura(Integer famQuantidadeSemanasCobertura) {
		this.famQuantidadeSemanasCobertura = famQuantidadeSemanasCobertura;
	}




}
