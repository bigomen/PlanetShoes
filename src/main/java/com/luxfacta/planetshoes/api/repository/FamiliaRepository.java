package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.Familia;

public interface FamiliaRepository extends PaginationRepository<Familia> {
   
    public Page<Familia> findAll(Pageable pageable);
    
    public Optional<Familia> findByFamMarcaIgnoreCase(String marca);

    public List<Familia> findByFamGeraPedido(String geraPedido);

    @Query("from Familia f order by f.famMarca")
    List<Familia> listaSimples();

    
    @Query("from Familia f where upper(f.famMarca) like nvl(upper(:famMarca), upper(famMarca)) order by f.famMarca")
    Page<Familia> listaFamilia(Pageable page, String famMarca);

}
