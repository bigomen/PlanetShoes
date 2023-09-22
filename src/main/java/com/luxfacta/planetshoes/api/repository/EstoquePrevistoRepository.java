package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.EstoquePrevisto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstoquePrevistoRepository extends PaginationRepository<EstoquePrevisto> {
   
    public Page<EstoquePrevisto> findAll(Pageable pageable);
    public Page<EstoquePrevisto> findByCalId(Pageable pageable, Long calId);
}
