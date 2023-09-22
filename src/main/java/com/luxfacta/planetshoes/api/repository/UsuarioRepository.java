/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luxfacta.planetshoes.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxfacta.planetshoes.api.model.Usuario;

public interface UsuarioRepository extends PaginationRepository<Usuario> {
    
    Optional<Usuario> findByUsuEmail(String usu_email);

    Optional<Usuario> findByUsuToken(String usu_reset_token);

        @Query("from Usuario us "
    		+ "LEFT JOIN FETCH us.lojaList l "
    		+ "where us.id = :usu_id and l.id = :loj_id")
    Optional<Usuario>findByIdAndLojId(@Param("usu_id") Long usu_id, @Param("loj_id") Long loj_id);

    Iterable<Usuario> findAllByOrderByUsuNomeAsc();

    @Query("select CASE WHEN count(u.id) > 0 THEN true ELSE false END from Usuario u where u.usuEmail = :email and u.id <> :id")
    Boolean validarEmail(String email, Long id);


    @Query("from Usuario u join u.grupo g where upper(usuNome) like nvl(upper(:usuNome),upper(usuNome)) and upper(g.gruDescricao) like nvl(upper(:gruDescricao),upper(gruDescricao)) order by u.usuNome")
    Page<Usuario> listaUsuarios(Pageable page, String usuNome, String gruDescricao);

}
