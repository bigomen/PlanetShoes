package com.luxfacta.planetshoes.api.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.constants.Constantes;
import com.luxfacta.planetshoes.api.model.Loja;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Mapper.ReferenceModel(className = Loja.class)
public class RestLoja implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    
    @NotBlank(message = "Nome")
    private String lojNome;
    
    @NotBlank(message = "CNPJ")
    private String lojCnpj;
    
    @JsonFormat(pattern= Constantes.PATTERN_DATA)
    private Date lojDataInicio;
    
    @JsonFormat(pattern=Constantes.PATTERN_DATA)
    private Date lojDataFim;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<RestUsuario> usuarioList;
    
    private RestMunicipio municipio;
    private RestTipoLoja tipoLoja;
    private RestFranqueado franqueado;
    private String frqId;
    
    @NotEmpty(message = "Código interno")
    private String lojCodigoInterno;

    
    @NotEmpty(message = "Municipio")
    private String munId;
    @NotEmpty(message = "Tipo Loja")
    private String tloId;

    private Integer lojQuantidadeSemanasAnalise;

    @NotEmpty(message = "Status Loja")
    private String lojStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLojNome() {
        return lojNome;
    }

    public void setLojNome(String lojNome) {
        this.lojNome = lojNome;
    }

    public String getLojCnpj() {
        return lojCnpj;
    }

    public void setLojCnpj(String lojCnpj) {
        this.lojCnpj = lojCnpj;
    }

    public Date getLojDataInicio() {
        return lojDataInicio;
    }

    public void setLojDataInicio(Date lojDataInicio) {
        this.lojDataInicio = lojDataInicio;
    }

    public Date getLojDataFim() {
        return lojDataFim;
    }

    public void setLojDataFim(Date lojDataFim) {
        this.lojDataFim = lojDataFim;
    }
    public RestMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(RestMunicipio municipio) {
        this.municipio = municipio;
    }

    public RestTipoLoja getTipoLoja() {
        return tipoLoja;
    }

    public void setTipoLoja(RestTipoLoja tipoLoja) {
        this.tipoLoja = tipoLoja;
    }



    public String getLojCodigoInterno() {
		return lojCodigoInterno;
	}

	public void setLojCodigoInterno(String lojCodigoInterno) {
		this.lojCodigoInterno = lojCodigoInterno;
	}

	public String getFrqId() {
		return frqId;
	}

	public void setFrqId(String frqId) {
		this.frqId = frqId;
	}

	public RestFranqueado getFranqueado() {
		return franqueado;
	}

	public void setFranqueado(RestFranqueado franqueado) {
		this.franqueado = franqueado;
	}

	public Set<RestUsuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(Set<RestUsuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

   
	public String getMunId() {
        return munId;
    }

    public void setMunId(String munId) {
        this.munId = munId;
    }

    public String getTloId() {
        return tloId;
    }

    public void setTloId(String tloId) {
        this.tloId = tloId;
    }

	public Integer getLojQuantidadeSemanasAnalise() {
		return lojQuantidadeSemanasAnalise;
	}

	public void setLojQuantidadeSemanasAnalise(Integer lojQuantidadeSemanasAnalise) {
		this.lojQuantidadeSemanasAnalise = lojQuantidadeSemanasAnalise;
	}

	public String getLojStatus() {
		return lojStatus;
	}

	public void setLojStatus(String lojStatus) {
		this.lojStatus = lojStatus;
	}
    
    
    
}
