package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.PageModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PaginationRepository<T extends PageModel> extends CrudRepository<T, Long> {

    @Query("select count(t.id) from #{#entityName} t")
    int countRows();
}
