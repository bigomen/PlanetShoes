package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Grupo;
import com.luxfacta.planetshoes.api.model.Permissao;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissaoRepository extends CrudRepository<Permissao,Long> {
   
    public List<Permissao> findAll(Sort sort);

    @Query("select g.permissaoList from Grupo g where g = :grupo ")
    List<Permissao> getPermissoes(Grupo grupo);

}
