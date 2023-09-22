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
@Table(name = "TIPO_LOJA")
@PageModel.SortField(fieldName = "tloDescricao")
public class TipoLoja extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Mapper.CryptoRequired
    @Basic(optional = false)
    @Column(name = "TLO_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_TIPO_LOJA")
    @jakarta.persistence.SequenceGenerator(name = "SQ_TIPO_LOJA", sequenceName = "SQ_TIPO_LOJA",allocationSize = 1)
    private Long id;
    @Column(name = "TLO_DESCRICAO")
    private String tloDescricao;
    @OneToMany(mappedBy = "tipoLoja", fetch = FetchType.LAZY)
    private List<Loja> lojaList;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTloDescricao() {
        return tloDescricao;
    }

    public void setTloDescricao(String tloDescricao) {
        this.tloDescricao = tloDescricao;
    }

    public List<Loja> getLojaList() {
        return lojaList;
    }

    public void setLojaList(List<Loja> lojaList) {
        this.lojaList = lojaList;
    }

}
