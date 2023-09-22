package com.luxfacta.planetshoes.api.repository;

import com.luxfacta.planetshoes.api.model.Artigo;
import com.luxfacta.planetshoes.api.model.Familia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtigoRepository extends PaginationRepository<Artigo> {
   
    public Page<Artigo> findAll(Pageable pageable);
    //List<Artigo> findByFamilia(Familia familia);
    List<Artigo> findByFamId(Long famId);
    
    public Optional<Artigo> findByArtDescricaoIgnoreCase(String descricao);

    @Query("from Artigo a order by a.artDescricao")
    List<Artigo> listaSimples();
    
    @Modifying
    @Query(value = "INSERT INTO ARTIGO "
    		+ "(ART_ID, ART_DESCRICAO, FAM_ID, ART_FABRICADO) "
    		+ "SELECT sq_artigo.nextval, trim(ARTK_DESCRICAO), f.fam_id, 1 "
    		+ "FROM (SELECT DISTINCT ARTK_DESCRICAO from VW_KUNDEN_ARTIGO) vka "
    		+ "CROSS JOIN (SELECT fam_id FROM FAMILIA f WHERE f.FAM_GERA_PEDIDO='S' AND upper(f.FAM_MARCA) LIKE '%PLANET%') f "
    		+ "WHERE NOT EXISTS (SELECT 1 FROM ARTIGO a WHERE trim(upper(a.ART_DESCRICAO)) = trim(upper(vka.ARTK_DESCRICAO)))", nativeQuery = true)
    void sincronizaArtigoKunden01();
 
    @Modifying
    @Query(value = "INSERT INTO COR "
    		+ "(cor_id, COR_DESCRICAO) "
    		+ "SELECT sq_cor.nextval, CORK_DESCRICAO "
    		+ "FROM (SELECT DISTINCT CORK_DESCRICAO from VW_KUNDEN_ARTIGO) vka "
    		+ "WHERE NOT EXISTS (SELECT 1 FROM COR c WHERE trim(upper(c.COR_DESCRICAO)) = trim(upper(vka.CORK_DESCRICAO)))", nativeQuery = true)
    void sincronizaArtigoKunden02();
 
    
    @Modifying
    @Query(value = "UPDATE ( "
    		+ "SELECT  a3.ART_ID ART_ID_PARA, anc.ART_ID ART_ID_DE "
    		+ "FROM ARTIGO a2 "
    		+ "JOIN ARTIGO_NUMERACAO_COR anc ON a2.art_id = anc.ART_ID "
    		+ "JOIN VW_KUNDEN_ARTIGO vka ON vka.ARTK_EAN  = anc.ANC_EAN "
    		+ "JOIN ARTIGO a3 ON a3.ART_DESCRICAO = vka.ARTK_DESCRICAO "
    		+ "JOIN FAMILIA f ON a2.fam_id = f.FAM_ID "
    		+ "WHERE f.FAM_GERA_PEDIDO='S' AND upper(f.FAM_MARCA) LIKE '%PLANET%' AND a2.ART_DESCRICAO <> vka.ARTK_DESCRICAO "
    		+ ")"
    		+ "SET ART_ID_DE = ART_ID_PARA", nativeQuery = true)
    void sincronizaArtigoKunden03();

    
    @Modifying
    @Query(value = "UPDATE ( "
    		+ "SELECT  c3.COR_ID COR_ID_PARA, anc.COR_ID COR_ID_DE "
    		+ "FROM COR c2 "
    		+ "JOIN ARTIGO_NUMERACAO_COR anc ON c2.cor_id = anc.COR_ID "
    		+ "JOIN VW_KUNDEN_ARTIGO vka ON vka.ARTK_EAN  = anc.ANC_EAN "
    		+ "JOIN COR c3 ON c3.COR_DESCRICAO = vka.CORK_DESCRICAO "
    		+ "WHERE upper(trim(c2.COR_DESCRICAO)) <> upper(trim(vka.CORK_DESCRICAO)) "
    		+ ")"
    		+ "SET COR_ID_DE = COR_ID_PARA", nativeQuery = true)
    void sincronizaArtigoKunden04();

    @Modifying
    @Query(value = "UPDATE ( "
    		+ "SELECT  ANC_NUMERACAO, ARTK_NUMERACAO "
    		+ "FROM ARTIGO_NUMERACAO_COR anc  "
    		+ "JOIN VW_KUNDEN_ARTIGO vka ON vka.ARTK_EAN  = anc.ANC_EAN "
    		+ "WHERE anc.ANC_NUMERACAO <> vka.ARTK_NUMERACAO "
    		+ ") "
    		+ "SET ANC_NUMERACAO = ARTK_NUMERACAO", nativeQuery = true)
    void sincronizaArtigoKunden05();
    
    @Modifying
    @Query(value = "INSERT INTO ARTIGO_NUMERACAO_COR "
    		+ "(ANC_ID, ANC_NUMERACAO, ANC_EAN, TAR_ID, ART_ID, COR_ID ) "
    		+ "SELECT SQ_ARTIGO_NUMERACAO_COR.nextval, vka.ARTK_NUMERACAO , vka.ARTK_EAN ,1, a.ART_ID , c.COR_ID "
    		+ "FROM VW_KUNDEN_ARTIGO vka "
    		+ "JOIN ARTIGO a  ON trim(upper(a.ART_DESCRICAO)) = trim(upper(vka.ARTK_DESCRICAO)) "
    		+ "JOIN COR c ON trim(upper(c.COR_DESCRICAO)) = trim(upper(vka.CORK_DESCRICAO)) "
    		+ "WHERE NOT exists(SELECT 1 FROM ARTIGO_NUMERACAO_COR anc WHERE anc.ANC_EAN = vka.ARTK_EAN)", nativeQuery = true)
    void sincronizaArtigoKunden06();

    
    @Modifying
    @Query(value = "delete ARTIGO a WHERE NOT EXISTS (SELECT 1 FROM ARTIGO_NUMERACAO_COR anc WHERE ANC.ART_ID=a.ART_ID)", nativeQuery = true)
    void sincronizaArtigoKunden07();

    
    @Modifying
    @Query(value = "DELETE  COR c WHERE NOT EXISTS (SELECT 1 FROM ARTIGO_NUMERACAO_COR anc WHERE ANC.COR_ID=c.COR_ID)", nativeQuery = true)
    void sincronizaArtigoKunden08();

}
