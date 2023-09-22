package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PedidoRepository extends PaginationRepository<Pedido> {
   
    public Page<Pedido> findAll(Pageable pageable);
    public Page<Pedido> findByLojId(Pageable pageable, Long lojId);
}
