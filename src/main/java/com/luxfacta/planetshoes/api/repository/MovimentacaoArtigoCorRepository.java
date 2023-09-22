package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.views.MovimentacaoArtigoCor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface MovimentacaoArtigoCorRepository extends CrudRepository<MovimentacaoArtigoCor,Long> {
   
    public List<MovimentacaoArtigoCor> findByArtIdAndSemIdAndLojId(Long artId, Long semId, Long lojId);
    
}
