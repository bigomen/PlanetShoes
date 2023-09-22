package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "MOVIMENTACAO")
@PageModel.SortField(fieldName = "id")
public class Movimentacao extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "MOV_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_MOVIMENTACAO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_MOVIMENTACAO", sequenceName = "SQ_MOVIMENTACAO",allocationSize = 1)
    private Long id;
    
    @JoinColumn(name = "SEM_ID", referencedColumnName = "SEM_ID", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Semana semana;
    
    @JoinColumn(name = "ANC_ID", referencedColumnName = "ANC_ID", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private ArtigoNumeracaoCor artigoNumeracaoCor;

    @JoinColumn(name = "LOJ_ID", referencedColumnName = "LOJ_ID", updatable = false, insertable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Loja loja;

    @CsvBindByPosition(position = 1)
    @Mapper.CryptoRequired
    @Column(name = "LOJ_ID")
    private Long lojId;

    @CsvBindByPosition(position = 2)
    @Mapper.CryptoRequired
    @Column(name = "ANC_ID")
    private Long ancId;

    @CsvBindByPosition(position = 3)
    @Mapper.CryptoRequired
    @Column(name = "SEM_ID")
    private Long semId;

    @CsvBindByPosition(position = 4)
    @Column(name = "MOV_SAIDA")
    private Long saida;

    @CsvBindByPosition(position = 5)
    @Column(name = "MOV_ENTRADA")
    private Long entrada;

    @CsvBindByPosition(position = 6)
    @Column(name = "MOV_SAIDA_OUTRA")
    private Long saidaOutra;

    @CsvBindByPosition(position = 7)
    @Column(name = "MOV_ENTRADA_OUTRA")
    private Long entradaOutra;

    @CsvBindByPosition(position = 8)
    @Column(name = "MOV_ESTOQUE_INFORMADO")
    private Long estoqueInformado;

    @CsvBindByPosition(position = 9)
    @Column(name = "MOV_ESTOQUE_CALCULADO")
    private Long estoqueCalculado;

    @CsvBindByPosition(position = 10)
    @Column(name = "MOV_ESTOQUE_REINICIA")
    private Short entroqueReinicia;

    
    public Long getLojId() {
        return lojId;
    }

    public void setLojId(Long lojId) {
        this.lojId = lojId;
    }

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

    public Semana getSemana() {
        return semana;
    }

    public void setSemana(Semana semana) {
        this.semana = semana;
    }
    
    public ArtigoNumeracaoCor getArtigoNumeracaoCor() {
        return artigoNumeracaoCor;
    }

    public void setArtigoNumeracaoCor(ArtigoNumeracaoCor artigoNumeracaoCor) {
        this.artigoNumeracaoCor = artigoNumeracaoCor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    

	public Long getSaida() {
		return saida;
	}

	public void setSaida(Long saida) {
		this.saida = saida;
	}

	public Long getEntrada() {
		return entrada;
	}

	public void setEntrada(Long entrada) {
		this.entrada = entrada;
	}

	public Long getSaidaOutra() {
		return saidaOutra;
	}

	public void setSaidaOutra(Long saidaOutra) {
		this.saidaOutra = saidaOutra;
	}

	public Long getEntradaOutra() {
		return entradaOutra;
	}

	public void setEntradaOutra(Long entradaOutra) {
		this.entradaOutra = entradaOutra;
	}

	public Long getEstoqueInformado() {
		return estoqueInformado;
	}

	public void setEstoqueInformado(Long estoqueInformado) {
		this.estoqueInformado = estoqueInformado;
	}

	public Long getEstoqueCalculado() {
		return estoqueCalculado;
	}

	public void setEstoqueCalculado(Long estoqueCalculado) {
		this.estoqueCalculado = estoqueCalculado;
	}

	public Long getAncId() {
		return ancId;
	}

	public void setAncId(Long ancId) {
		this.ancId = ancId;
	}

	

	public Short getEntroqueReinicia() {
		return entroqueReinicia;
	}

	public void setEntroqueReinicia(Short entroqueReinicia) {
		this.entroqueReinicia = entroqueReinicia;
	}


    
}
