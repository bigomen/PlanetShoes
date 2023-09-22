package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Cor;
import com.luxfacta.planetshoes.api.model.VendaProjetada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface VendaProjetadaRepository extends PaginationRepository<VendaProjetada> {
   
    public Page<VendaProjetada> findAll(Pageable pageable);

    public List<VendaProjetada> findByCalId(Long calId);

    List<VendaProjetada> findByCalId(Sort sort, Long calId);

    List<VendaProjetada> findByCor(Cor cor);

    List<VendaProjetada> findAllByCorIdAndArtIdAndCalIdOrderById(Long corId, Long artId, Long calId);

}
