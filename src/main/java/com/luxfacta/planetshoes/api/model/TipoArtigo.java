/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luxfacta.planetshoes.api.model;

import com.luxfacta.planetshoes.api.base.IDatabaseModel;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TIPO_ARTIGO")
@PageModel.SortField(fieldName = "tarDescricao")
public class TipoArtigo extends PageModel implements Serializable, IDatabaseModel {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Mapper.CryptoRequired
    @Column(name = "TAR_ID")
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "SQ_TIPO_ARTIGO")
    @jakarta.persistence.SequenceGenerator(name = "SQ_TIPO_ARTIGO", sequenceName = "SQ_TIPO_ARTIGO",allocationSize = 1)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TAR_DESCRICAO")
    private String tarDescricao;

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getTarDescricao() {
		return tarDescricao;
	}

	public void setTarDescricao(String descricao) {
		this.tarDescricao = descricao;
	}

    
}
