package com.luxfacta.planetshoes.api.rest;

import java.io.Serial;
import java.io.Serializable;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Franqueado;

import jakarta.validation.constraints.NotEmpty;

@Mapper.ReferenceModel(className = Franqueado.class)
public class RestFranqueado implements Serializable, IRestModel {
	@Serial
    private static final long serialVersionUID = 1L;
	
	@RestId
    private String id;
    
	@NotEmpty(message = "Descrição")
    private String frqNome;

    private String frqCodigo;
    
    private String redId;

    private RestRede rede;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
    public String getRedId() {
		return redId;
	}

	public void setRedId(String redId) {
		this.redId = redId;
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

	
	public RestRede getRede() {
		return rede;
	}

	public void setRede(RestRede rede) {
		this.rede = rede;
	}
    
    
    
}
