package com.luxfacta.planetshoes.api.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.constants.Constantes;
import com.luxfacta.planetshoes.api.model.Semana;

import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Mapper.ReferenceModel(className = Semana.class)
public class RestSemana implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    @NotNull(message = "Numero")
    private Long semNumero;
    @NotNull(message = "Ano")
    private Long semAno;
    @NotNull(message = "Data inicio")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private Date semDataInicio;
    @NotNull(message = "Data fim")
    @JsonFormat(pattern = Constantes.PATTERN_DATA)
    private Date semDataFim;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSemNumero() {
        return semNumero;
    }

    public void setSemNumero(long semNumero) {
        this.semNumero = semNumero;
    }

    public long getSemAno() {
        return semAno;
    }

    public void setSemAno(long semAno) {
        this.semAno = semAno;
    }

    public Date getSemDataInicio() {
        return semDataInicio;
    }

    public void setSemDataInicio(Date semDataInicio) {
        this.semDataInicio = semDataInicio;
    }

    public Date getSemDataFim() {
        return semDataFim;
    }

    public void setSemDataFim(Date semDataFim) {
        this.semDataFim = semDataFim;
    }


}
