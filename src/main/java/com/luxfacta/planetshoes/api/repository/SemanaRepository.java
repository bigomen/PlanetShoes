package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Semana;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface SemanaRepository extends PaginationRepository<Semana> {
   
    Page<Semana> findAll(Pageable pageable);
    
    Optional<Semana> findBySemAnoAndSemNumero(Long ano, Long numero);
    
    @Query(value = "from Semana where :data between semDataInicio and semDataFim")
    Optional<Semana> findByData(@Param("data") Date data);

    @Query("from Semana s order by s.semNumero")
    List<Semana> listaSimples();

    @Query("from Semana s where semAno = nvl(:semAno, semAno) and semNumero = nvl(:semNumero, semNumero) and semDataInicio <= nvl(:data, semDataInicio) and semDataFim >= nvl(:data, semDataFim)  order by s.semAno, s.semNumero")
    Page<Semana> listaSemanas(Pageable page, Long semAno, Long semNumero, Date data);


}
