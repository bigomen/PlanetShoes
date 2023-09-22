package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxfacta.planetshoes.api.model.ArtigoNumeracaoCor;


public interface ArtigoNumeracaoCorRepository extends PaginationRepository<ArtigoNumeracaoCor> {
   
    public Page<ArtigoNumeracaoCor> findAll(Pageable pageable);
    
    @Query(value = "from ArtigoNumeracaoCor where artId=:artId and corId=:corId and ancNumeracao=:numeracao")
    public Optional<ArtigoNumeracaoCor> findByArtigoNumeracaoCor(@Param("artId") Long artId, @Param("corId") Long corId, @Param("numeracao") Integer numeracao);
    
    public Optional<ArtigoNumeracaoCor> findByAncEan(@Param("ean") String ean);

    List<ArtigoNumeracaoCor> findByCorId(Long corId);

    List<ArtigoNumeracaoCor> findByArtId(Long artId);

    @Modifying
    @Query("delete from ArtigoNumeracaoCor")
    void apagar();

    List<ArtigoNumeracaoCor> findAllByArtIdAndCorIdAndTarId(Long artId, Long corId, Long tarId);

    @Query("from ArtigoNumeracaoCor anc join anc.cor c join anc.artigo a join a.familia f where upper(f.famMarca) like nvl(upper(:famMarca),upper(f.famMarca)) and upper(a.artDescricao) like nvl(upper(:artDescricao),upper(a.artDescricao)) and upper(c.corDescricao) like nvl(upper(:corDescricao),upper(c.corDescricao)) and anc.ancNumeracao = nvl(:ancNumeracao,anc.ancNumeracao) and f.famGeraPedido = nvl(:famGeraPedido,famGeraPedido) order by f.famMarca, a.artDescricao, c.corDescricao, anc.ancNumeracao")
    Page<ArtigoNumeracaoCor> listaArtigos(Pageable page, String famMarca, String artDescricao, String corDescricao, Integer ancNumeracao, String famGeraPedido);

    
    
}
