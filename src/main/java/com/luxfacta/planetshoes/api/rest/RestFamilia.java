package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Familia;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Familia.class)
public class RestFamilia implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Marca")
    private String famMarca;
    @NotNull(message = "Gera pedido")
    private Boolean famGeraPedido;

    
    private Integer famQuantidadeSemanasAnalise;
    private Integer famQuantidadeSemanasPrevisao;
    private Integer famQuantidadeSemanasGrade;
    private Integer famQuantidadeSemanasCobertura;
    private String famPonderacao;

    

    public String getFamPonderacao() {
		return famPonderacao;
	}

	public void setFamPonderacao(String famPonderacao) {
		this.famPonderacao = famPonderacao;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFamMarca() {
        return famMarca;
    }

    public void setFamMarca(String famMarca) {
        this.famMarca = famMarca;
    }



    public Boolean getFamGeraPedido() {
        return famGeraPedido;
    }

    public void setFamGeraPedido(Boolean famGeraPedido) {
        this.famGeraPedido = famGeraPedido;
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
