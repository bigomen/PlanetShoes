package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalCorPorLojaArtigo;

@Repository
public interface MovimentacaoTotalCorPorLojaArtigoRepository extends CrudRepository<MovimentacaoTotalCorPorLojaArtigo, Long> {

    List<MovimentacaoTotalCorPorLojaArtigo> findAllBySemIdAndLojIdAndArtId(Long semId, Long lojId, Long artId);
}
