package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luxfacta.planetshoes.api.model.kunden.KundenPedidoAberto;


public interface KundenPedidoAbertoRepository extends CrudRepository<KundenPedidoAberto, Long> {
   

    List<KundenPedidoAberto> findByLojIdAndAncId(Long lojId, Long ancId);


    
    
}
