package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Telefone;
import com.luxfacta.planetshoes.api.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TelefoneRepository extends CrudRepository<Telefone,Long> {
   
    public Page<Telefone> findAll(Pageable pageable);
    List<Telefone> findByUsuario(Usuario usuario);
}
