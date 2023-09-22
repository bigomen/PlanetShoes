/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.TipoArtigo;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = TipoArtigo.class)
public class RestTipoArtigo implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty(message = "Descricao")
    private String tarDescricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarDescricao() {
        return tarDescricao;
    }

    public void setTarDescricao(String tarDescricao) {
        this.tarDescricao = tarDescricao;
    }

    
}
