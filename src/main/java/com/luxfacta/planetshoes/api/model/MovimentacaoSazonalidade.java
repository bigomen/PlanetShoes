package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "MOVIMENTACAO_SAZONALIDADE")
@PageModel.SortField(fieldName = "id")
public class MovimentacaoSazonalidade extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MSA_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_MOVIMENTACAO_SAZONALIDADE")
    @jakarta.persistence.SequenceGenerator(name = "SQ_MOVIMENTACAO_SAZONALIDADE", sequenceName = "SQ_MOVIMENTACAO_SAZONALIDADE",allocationSize = 1)
    @Mapper.CryptoRequired
    private Long id;
    
    @Column(name = "MSA_FATOR_SAZONALIDADE")
    private Double msaFatorSazonalidade;
    
    @Column(name = "MSA_QUANTIDADE_SAZONALIZADA")
    private Double msaQuantidadeSazonalizada;
    
    @Column(name = "MSA_FATOR_PONDERACAO")
    private Double msaFatorPonderacao;
    
    @Column(name = "MSA_QUANTIDADE")
    private Long msaQuantidade;

    @Column(name = "MSA_ESTOQUE")
    private Long msaEstoque;

    @Column(name = "MSA_ENTRADA")
    private Long msaEntrada;

    


	@JoinColumn(name = "COR_ID", referencedColumnName = "COR_ID", insertable = false, updatable = false)
    @ManyToOne
    private Cor cor;

    @Mapper.CryptoRequired
    @Column(name = "COR_ID")
    private Long corId;

    @JoinColumn(name = "ART_ID", referencedColumnName = "ART_ID", insertable = false, updatable = false)
    @ManyToOne
    private Artigo artigo;

    @Mapper.CryptoRequired
    @Column(name = "ART_ID")
    private Long artId;
    
    @JoinColumn(name = "SEM_ID", referencedColumnName = "SEM_ID", insertable = false, updatable = false)
    @ManyToOne
    private Semana semana;

    @Mapper.CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

    @JoinColumn(name = "CAL_ID", referencedColumnName = "CAL_ID", updatable = false, insertable = false)
    @ManyToOne
    private Calculo calculo;

    @Mapper.CryptoRequired
    @Column(name = "CAL_ID")
    private Long calId;

    public Long getSemId() {
        return semId;
    }

    public void setSemId(Long semId) {
        this.semId = semId;
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

	public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }

	public Double getMsaFatorSazonalidade() {
		return msaFatorSazonalidade;
	}

	public void setMsaFatorSazonalidade(Double fatorSazonalidade) {
		this.msaFatorSazonalidade = fatorSazonalidade;
	}

	public Double getMsaQuantidadeSazonalizada() {
		return msaQuantidadeSazonalizada;
	}

	public void setMsaQuantidadeSazonalizada(Double quantidadeSazonalizada) {
		this.msaQuantidadeSazonalizada = quantidadeSazonalizada;
	}

	public Double getMsaFatorPonderacao() {
		return msaFatorPonderacao;
	}

	public void setMsaFatorPonderacao(Double fatorPonderacao) {
		this.msaFatorPonderacao = fatorPonderacao;
	}

	public Long getMsaQuantidade() {
		return msaQuantidade;
	}

	public void setMsaQuantidade(Long quantidadeAjustada) {
		this.msaQuantidade = quantidadeAjustada;
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

    public Calculo getCalculo() {
        return calculo;
    }

    public void setCalculo(Calculo calculo) {
        this.calculo = calculo;
    }

    public Long getCalId() {
        return calId;
    }

    public void setCalId(Long calId) {
        this.calId = calId;
    }

	public Long getMsaEstoque() {
		return msaEstoque;
	}

	public void setMsaEstoque(Long msaEstoque) {
		this.msaEstoque = msaEstoque;
	}
    
    public Long getMsaEntrada() {
		return msaEntrada;
	}

	public void setMsaEntrada(Long msaEntrada) {
		this.msaEntrada = msaEntrada;
	}
    
}
