package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalLojaPorFranqueadoArtigoCor;

@Repository
public interface MovimentacaoTotalLojaPorFranqueadoArtigoCorRepository extends CrudRepository<MovimentacaoTotalLojaPorFranqueadoArtigoCor, Long> {

    List<MovimentacaoTotalLojaPorFranqueadoArtigoCor> findAllBySemIdAndArtIdAndCorIdAndFrqId(Long semId, Long artId, Long corId, Long frqId);
}
