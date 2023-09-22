package com.luxfacta.planetshoes.api.repository;

import org.springframework.data.domain.Sort;

import com.luxfacta.planetshoes.api.model.Regiao;



public interface RegiaoRepository extends PaginationRepository<Regiao> {
   
    public Iterable<Regiao> findAll(Sort pageable);


}
