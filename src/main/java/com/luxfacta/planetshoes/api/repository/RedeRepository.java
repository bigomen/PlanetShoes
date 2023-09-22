package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Rede;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RedeRepository extends PaginationRepository<Rede> {

    //@Query("select r from Loja l join l.rede r where l.id = :id")
    //Rede getRedeFromLoja(Long id);

    List<Rede> findAll(Sort sort);

    Boolean existsByRedDescricao(String redDescricao);

    @Query("from Rede r order by r.redDescricao")
    List<Rede> listaSimples();

    @Query("select CASE WHEN count(r.id) > 0 THEN true ELSE false END from Rede r where r.redDescricao = :descricao and r.id <> :id")
    Boolean validarDescricao(String descricao, Long id);
}
