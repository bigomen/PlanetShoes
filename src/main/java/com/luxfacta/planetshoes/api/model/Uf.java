/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "UF")
@NamedQueries({
    @NamedQuery(name = "Uf.findAll", query = "SELECT u FROM Uf u")})
@PageModel.SortField(fieldName = "ufNome")
public class Uf extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "UF_ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "UF_NOME")
    private String ufNome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uf")
    private List<Municipio> municipioList;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUfNome() {
        return ufNome;
    }

    public void setUfNome(String ufNome) {
        this.ufNome = ufNome;
    }

    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    
}
