package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Municipio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface MunicipioRepository extends CrudRepository<Municipio,Long> {
   
    public Page<Municipio> findAll(Pageable pageable);
    
    public List<Municipio> findByUfIdOrderByMunNome(Long uf);
    
    
}
