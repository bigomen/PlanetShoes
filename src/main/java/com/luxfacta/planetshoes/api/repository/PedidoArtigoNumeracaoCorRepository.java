package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.PedidoArtigoNumeracaoCor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoArtigoNumeracaoCorRepository extends CrudRepository<PedidoArtigoNumeracaoCor, Long> {
}
