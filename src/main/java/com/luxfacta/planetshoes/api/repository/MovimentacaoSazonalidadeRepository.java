package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Cor;
import com.luxfacta.planetshoes.api.model.MovimentacaoSazonalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MovimentacaoSazonalidadeRepository extends PaginationRepository<MovimentacaoSazonalidade> {
   
    public Page<MovimentacaoSazonalidade> findAll(Pageable pageable);

    List<MovimentacaoSazonalidade> findByCor(Cor cor);

    List<MovimentacaoSazonalidade> findBySemId(Pageable pageable, Long semId);

    Iterable<MovimentacaoSazonalidade> findByArtIdAndCorIdAndCalIdOrderById(Long artId, Long corId, Long calId);

}
