package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.TipoArtigo;

public interface TipoArtigoRepository extends PaginationRepository<TipoArtigo> {

	Iterable<TipoArtigo> findAll(Sort sort);
    
    public Optional<TipoArtigo> findByTarDescricaoIgnoreCase(String descricao);

    Boolean existsByTarDescricao(String tarDescricao);

    @Query("select CASE WHEN count(ta.id) > 0 THEN true ELSE false END from TipoArtigo ta where ta.tarDescricao = :tarDescricao and ta.id <> :id")
    Boolean validarDescricao(String tarDescricao, Long id);

    @Query("from TipoArtigo ta order by ta.tarDescricao")
    List<TipoArtigo> listaSimples();
}
