package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalLojaPorFranqueado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoTotalLojaPorFranqueadoRepository extends CrudRepository<MovimentacaoTotalLojaPorFranqueado, Long> {

    List<MovimentacaoTotalLojaPorFranqueado> findAllBySemIdOrderByLojNome(Long semId);
    List<MovimentacaoTotalLojaPorFranqueado> findAllBySemIdAndFrqId(Long semId, Long redId);
}
