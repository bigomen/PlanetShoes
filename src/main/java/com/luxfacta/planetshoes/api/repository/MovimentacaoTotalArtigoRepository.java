package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalArtigo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoTotalArtigoRepository extends CrudRepository<MovimentacaoTotalArtigo, Long> {

    List<MovimentacaoTotalArtigo> findAllBySemId(Long semId);
}
