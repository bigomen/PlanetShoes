package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Cor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CorRepository extends PaginationRepository<Cor> {
   
    public Page<Cor> findAll(Pageable pageable);

    public Optional<Cor> findByCorDescricaoIgnoreCase(String descricao);

    @Query("from Cor c order by c.corDescricao")
    List<Cor> listaSimples();

    
    @Query("from Cor c where upper(c.corDescricao) like nvl(upper(:corDescricao),upper(corDescricao)) and (c.corCodigoErp = nvl(:corCodigoErp,corCodigoErp) or (:corCodigoErp is null and corCodigoErp is null))  order by c.corDescricao")
    Page<Cor> listaCores(Pageable p, String corDescricao, String corCodigoErp);

    
}
