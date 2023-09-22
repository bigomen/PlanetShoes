package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Mapper.ReferenceModel(className = Usuario.class)
public class RestUsuario implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @RestId
    private String id;
    @NotEmpty(message = "Nome")
    private String usuNome;
    @NotEmpty(message = "Email")
    @Email(message = "Email invalido")
    private String usuEmail;
    private Set<RestLoja> lojaList;
    private List<RestTelefone> telefoneList;
    private RestGrupo grupo;
    @NotEmpty(message = "Grupo")
    private String gruId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuNome() {
        return usuNome;
    }

    public void setUsuNome(String usuNome) {
        this.usuNome = usuNome;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Set<RestLoja> getLojaList() {
        return lojaList;
    }

    public void setLojaList(Set<RestLoja> lojaList) {
        this.lojaList = lojaList;
    }

    public List<RestTelefone> getTelefoneList() {
        return telefoneList;
    }

    public void setTelefoneList(List<RestTelefone> telefoneList) {
        this.telefoneList = telefoneList;
    }

    public RestGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(RestGrupo grupo) {
        this.grupo = grupo;
    }

    public String getGruId() {
        return gruId;
    }

    public void setGruId(String gruId) {
        this.gruId = gruId;
    }
}
