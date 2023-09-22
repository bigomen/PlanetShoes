package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luxfacta.planetshoes.api.model.kunden.KundenNotaFiscalItemSemana;


public interface KundenNotaFiscalItemSemanaRepository extends CrudRepository<KundenNotaFiscalItemSemana, Long> {
   

    List<KundenNotaFiscalItemSemana> findByLojIdAndSemIdInAndAncId(Long lojId, Long[] semId, Long ancId);


    
    
}
