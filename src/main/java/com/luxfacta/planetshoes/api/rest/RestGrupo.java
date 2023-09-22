package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Grupo;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Mapper.ReferenceModel(className = Grupo.class)
public class RestGrupo implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotEmpty
    private String gruDescricao;
    private Set<RestPermissao> permissaoList;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGruDescricao() {
        return gruDescricao;
    }

    public void setGruDescricao(String gruDescricao) {
        this.gruDescricao = gruDescricao;
    }

    public Set<RestPermissao> getPermissaoList() {
        return permissaoList;
    }

    public void setPermissaoList(Set<RestPermissao> permissaoList) {
        this.permissaoList = permissaoList;
    }


    
}
