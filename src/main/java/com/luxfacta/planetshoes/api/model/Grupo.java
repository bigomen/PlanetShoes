package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GRUPO")
@PageModel.SortField(fieldName = "gruDescricao")
public class Grupo extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "GRU_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_GRUPO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_GRUPO", sequenceName = "SQ_GRUPO",allocationSize = 1)
    private Long id;
    @Basic(optional = false)
    @Column(name = "GRU_DESCRICAO")
    private String gruDescricao;
    @JoinTable(name = "GRUPO_PERMISSAO", joinColumns = {
        @JoinColumn(name = "GRU_ID", referencedColumnName = "GRU_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PRM_ID", referencedColumnName = "PRM_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Permissao> permissaoList = new HashSet<>();

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGruDescricao() {
        return gruDescricao;
    }

    public void setGruDescricao(String gruDescricao) {
        this.gruDescricao = gruDescricao;
    }

    public Set<Permissao> getPermissaoList() {
        return permissaoList;
    }

    public void setPermissaoList(Set<Permissao> permissaoList) {
        this.permissaoList = permissaoList;
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
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.luxfacta.planetshoes.api.model.Grupo[ gruId=" + id + " ]";
    }
    
}
