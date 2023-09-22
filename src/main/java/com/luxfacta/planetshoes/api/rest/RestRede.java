package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Rede;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Mapper.ReferenceModel(className = Rede.class)
public class RestRede implements Serializable, IRestModel {
	@Serial
    private static final long serialVersionUID = 1L;
	
	@RestId
    private String id;
    
	@NotEmpty(message = "Descrição")
    private String redDescricao;

    private List<RestFranqueado> franqueadoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getRedDescricao() {
		return redDescricao;
	}

	public void setRedDescricao(String redDescricao) {
		this.redDescricao = redDescricao;
	}

	public List<RestFranqueado> getFranqueadoList() {
		return franqueadoList;
	}

	public void setFranqueadoList(List<RestFranqueado> franqueadoList) {
		this.franqueadoList = franqueadoList;
	}

    
    
    
    
}
