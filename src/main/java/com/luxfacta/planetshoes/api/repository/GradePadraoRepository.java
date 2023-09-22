package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.GradePadrao;
import com.luxfacta.planetshoes.api.model.Loja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GradePadraoRepository extends PaginationRepository<GradePadrao> {
   
    public Page<GradePadrao> findAll(Pageable pageable);
    //public Page<GradePadrao> findByLojId(Pageable pageable, Long lojId);
    //public List<GradePadrao> findByLojId(Long lojId);


    //List<GradePadrao> findAllByLojIdAndCalIdAndCorIdAndArtId(Long lojId, Long calId, Long corId, Long artId);

}
