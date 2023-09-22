package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalFranqueado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoTotalFranqueadoRepository extends CrudRepository<MovimentacaoTotalFranqueado, Long> {

    List<MovimentacaoTotalFranqueado> findAllBySemId(Long semId);
}
