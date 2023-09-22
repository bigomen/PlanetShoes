package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Uf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UfRepository extends CrudRepository<Uf,Long> {
   
    public Page<Uf> findAll(Pageable pageable);

    List<Uf> findAll(Sort sort);

}
