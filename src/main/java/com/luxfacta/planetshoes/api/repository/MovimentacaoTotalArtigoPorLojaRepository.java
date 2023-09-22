package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalArtigoPorLoja;

@Repository
public interface MovimentacaoTotalArtigoPorLojaRepository extends CrudRepository<MovimentacaoTotalArtigoPorLoja, Long> {

    List<MovimentacaoTotalArtigoPorLoja> findAllBySemIdAndLojId(Long semId, Long lojId);
}
