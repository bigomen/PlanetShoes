package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.TipoLoja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TipoLojaRepository extends PaginationRepository<TipoLoja>{
   
    Iterable<TipoLoja> findAll(Pageable pageable);

    List<TipoLoja> findAll(Sort sort);

    Boolean existsByTloDescricao(String tloDescricao);

    @Query("select CASE WHEN count(tl.id) > 0 THEN TRUE ELSE FALSE END from TipoLoja tl where tl.tloDescricao = :tloDescricao and tl.id <> :id")
    Boolean validarDescricao(String tloDescricao, Long id);

    @Query("from TipoLoja tl order by tl.tloDescricao")
    List<TipoLoja> listaSimples();
}
