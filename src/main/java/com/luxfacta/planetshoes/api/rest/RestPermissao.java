package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Permissao;

import java.io.Serial;
import java.io.Serializable;

@Mapper.ReferenceModel(className = Permissao.class)
public class RestPermissao implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private String prmDescricao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrmDescricao() {
        return prmDescricao;
    }

    public void setPrmDescricao(String prmDescricao) {
        this.prmDescricao = prmDescricao;
    }


    
}
