package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StatusPedidoRepository extends CrudRepository<StatusPedido,Long> {
   
    public Page<StatusPedido> findAll(Pageable pageable);
}
