package com.luxfacta.planetshoes.api.rest;

import com.luxfacta.planetshoes.api.base.IRestModel;
import com.luxfacta.planetshoes.api.model.Calculo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Mapper.ReferenceModel(className = Calculo.class)
public class RestCalculo implements Serializable, IRestModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @RestId
    private String id;
    private Date calData;
    private RestLoja loja;
    private RestSemana semanaDe;
    private RestSemana semanaAte;
    private String lojId;
    private String semIdDe;
    private String semIdAte;
    private String usuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCalData() {
        return calData;
    }

    public void setCalData(Date calData) {
        this.calData = calData;
    }

    public RestLoja getLoja() {
        return loja;
    }

    public void setLoja(RestLoja loja) {
        this.loja = loja;
    }

    public RestSemana getSemanaDe() {
        return semanaDe;
    }

    public void setSemanaDe(RestSemana semanaDe) {
        this.semanaDe = semanaDe;
    }

    public RestSemana getSemanaAte() {
        return semanaAte;
    }

    public void setSemanaAte(RestSemana semanaAte) {
        this.semanaAte = semanaAte;
    }

    public String getLojId() {
        return lojId;
    }

    public void setLojId(String lojId) {
        this.lojId = lojId;
    }

    public String getSemIdDe() {
        return semIdDe;
    }

    public void setSemIdDe(String semIdDe) {
        this.semIdDe = semIdDe;
    }

    public String getSemIdAte() {
        return semIdAte;
    }

    public void setSemIdAte(String semIdAte) {
        this.semIdAte = semIdAte;
    }

    public String getUsuId() {
        return usuId;
    }

    public void setUsuId(String usuId) {
        this.usuId = usuId;
    }
}
