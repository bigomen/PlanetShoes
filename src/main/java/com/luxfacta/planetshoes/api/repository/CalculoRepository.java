package com.luxfacta.planetshoes.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.Calculo;


@Repository
public interface CalculoRepository extends CrudRepository<Calculo,Long> {
   
    public Page<Calculo> findAll(Pageable pageable);

    Optional<Calculo> findBySemIdDeAndLojId(Long semIdDe, Long lojId);
    
    Iterable<Calculo> findBySemIdDe(Long semIdDe);
}
