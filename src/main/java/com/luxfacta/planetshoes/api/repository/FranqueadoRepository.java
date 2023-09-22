package com.luxfacta.planetshoes.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.Franqueado;

public interface FranqueadoRepository extends PaginationRepository<Franqueado> {


    List<Franqueado> findAll(Sort sort);
    
    @Query("from Franqueado r order by r.frqNome")
    List<Franqueado> listaSimples();

    Boolean existsByFrqCodigoAndRedId(String frqCodigo, Long redId);
    
    @Query("select CASE WHEN count(f.id) > 0 THEN true ELSE false END from Franqueado f where f.redId=:redId and f.frqCodigo = :frqCodigo and f.id <> :id")
    Boolean validarCodigo(String frqCodigo, Long redId, Long id);

    @Query("from Franqueado f where upper(f.frqNome) like nvl(upper(:frqNome),upper(f.frqNome)) and upper(f.frqCodigo) = nvl(upper(:frqCodigo),upper(f.frqCodigo)) order by f.frqNome, f.frqCodigo")
    Page<Franqueado> listaFranqueados(Pageable page, String frqCodigo, String frqNome);

}
