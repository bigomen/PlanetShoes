package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.SolicitacaoPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SolicitacaoPedidoRepository extends PaginationRepository<SolicitacaoPedido> {
   
    public Page<SolicitacaoPedido> findAll(Pageable pageable);
}
