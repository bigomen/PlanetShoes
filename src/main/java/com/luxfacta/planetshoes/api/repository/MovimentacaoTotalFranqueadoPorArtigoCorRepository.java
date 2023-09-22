package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalFranqueadoPorArtigoCor;

@Repository
public interface MovimentacaoTotalFranqueadoPorArtigoCorRepository extends CrudRepository<MovimentacaoTotalFranqueadoPorArtigoCor, Long> {

    List<MovimentacaoTotalFranqueadoPorArtigoCor> findAllBySemIdAndArtIdAndCorId(Long semId, Long artId, Long corId);
}
