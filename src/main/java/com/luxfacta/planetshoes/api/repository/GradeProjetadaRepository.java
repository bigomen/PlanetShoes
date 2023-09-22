package com.luxfacta.planetshoes.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.GradeProjetada;


public interface GradeProjetadaRepository extends PaginationRepository<GradeProjetada> {
   
    public Page<GradeProjetada> findAll(Pageable pageable);
    public Page<GradeProjetada> findByCalId(Pageable pageable, Long calId);

    Optional<GradeProjetada> findByAncIdAndCalId(Long ancId, Long calId);

    @Query("from GradeProjetada gp join gp.artigoNumeracaoCor a join gp.calculo c where a.corId=:corId and a.artId=:artId and c.semIdDe=:semId and c.lojId=:lojId")
    Iterable<GradeProjetada> findByArtIdAndCorIdAndSemIdAndLojId(Long artId, Long corId, Long semId, Long lojId);

    
    @Modifying
    @Query("update GradeProjetada gp set gp.gprPedidoFabrica = :pedido where gp.id = :id")
    void atualizarPedido(Long pedido, Long id);
}
