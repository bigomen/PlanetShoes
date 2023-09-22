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
@Table(name = "PERMISSAO")
@PageModel.SortField(fieldName = "prmDescricao")
public class Permissao extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PRM_ID")
    @Mapper.CryptoRequired
    private Long id;
    @Basic(optional = false)
    @Column(name = "PRM_DESCRICAO")
    private String prmDescricao;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Grupo> grupoList;

    public Permissao() {
    }

    public Permissao(Long prmId) {
        this.id = prmId;
    }

    public Permissao(Long prmId, String prmDescricao) {
        this.id = prmId;
        this.prmDescricao = prmDescricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrmDescricao() {
        return prmDescricao;
    }

    public void setPrmDescricao(String prmDescricao) {
        this.prmDescricao = prmDescricao;
    }

    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luxfacta.planetshoes.api.model.Permissao[ prmId=" + id + " ]";
    }
    
}
