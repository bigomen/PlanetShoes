package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.Grupo;


public interface GrupoRepository extends PaginationRepository<Grupo> {
   
    public Page<Grupo> findAll(Pageable pageable);

    @Query("from Grupo g order by g.gruDescricao")
    List<Grupo> listaSimples();
    
    @Query("from Grupo g where upper(g.gruDescricao) like nvl(upper(:gruDescricao),upper(g.gruDescricao)) order by g.gruDescricao" )
    Page<Grupo> listaGrupos(Pageable page, String gruDescricao);
    
    
}
