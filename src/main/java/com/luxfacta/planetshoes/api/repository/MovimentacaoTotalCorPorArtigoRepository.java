package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalCorPorArtigo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoTotalCorPorArtigoRepository extends CrudRepository<MovimentacaoTotalCorPorArtigo, Long> {

    List<MovimentacaoTotalCorPorArtigo> findAllBySemIdAndArtIdAndCorId(Long semId, Long artId, Long corId);

    List<MovimentacaoTotalCorPorArtigo> findAllBySemIdAndArtId(Long semId, Long artId);
}
