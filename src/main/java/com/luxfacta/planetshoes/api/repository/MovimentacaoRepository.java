package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxfacta.planetshoes.api.model.Movimentacao;


public interface MovimentacaoRepository extends PaginationRepository<Movimentacao> {
   
    public Page<Movimentacao> findAll(Pageable pageable);
    public Page<Movimentacao> findByLojIdAndSemId(Pageable pageable, Long lojId, Long semId);
    
    public Optional<Movimentacao> findByLojIdAndSemIdAndAncId(Long lojId, Long semId, Long ancId);

    @Query("from Movimentacao m join fetch m.artigoNumeracaoCor a where m.lojId = :lojId and m.semId in (:semId) and a.artId=:artId")
    public List<Movimentacao> findByLojIdAndArtIdAndSemIdIn(@Param("lojId") Long lojId, @Param("artId") Long artId, @Param("semId") Long[] semId);

}
