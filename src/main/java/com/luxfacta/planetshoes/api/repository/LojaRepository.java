package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.luxfacta.planetshoes.api.model.Loja;




public interface LojaRepository extends PaginationRepository<Loja> {
   
    Page<Loja> findAll(Pageable pageable);

    
    Optional<Loja> findByLojCnpj(String cnpj);

    @Query("select CASE WHEN count(l.id) > 0 THEN true ELSE false END from Loja l where l.lojCnpj = :lojCnpj and l.id <> :id")
    Boolean validarCnpj(String lojCnpj, Long id);

    @Query("from Loja l join fetch l.usuarioList u where u.id = :id")
    List<Loja> lojasUsuario(Long id);

    @Query("from Loja l order by l.lojNome")
    List<Loja> listaSimples();

    @Query("from Loja l join l.franqueado f where f.id = :id order by l.lojNome")
    List<Loja> lojasDoFranqueado(Long id);

    @Query("from Loja l join l.franqueado f join f.rede r where r.id = :id order by l.lojNome")
    List<Loja> lojasDaRede(Long id);
    
    
    @Query("from Loja f where upper(f.lojNome) like nvl(upper(:lojNome),upper(f.lojNome)) and upper(f.lojCodigoInterno) = nvl(upper(:lojCodigoInterno),upper(f.lojCodigoInterno)) and f.lojCnpj = nvl(:lojCnpj,f.lojCnpj) order by f.lojNome, f.lojCnpj")
    Page<Loja> listaLojas(Pageable page, String lojCnpj, String lojCodigoInterno, String lojNome);


    @Modifying
    @Query(value = "UPDATE "
    		+ "LOJA l "
    		+ "SET LOJ_STATUS = 'I', LOJ_CODIGO_INTERNO = null "
    		+ "WHERE NOT EXISTS (SELECT 1 FROM kunden.cliente_planet_v@DBL_KUNDEN k where k.cnpj_cpf = l.LOJ_CNPJ) "
    		+ "AND loj_status <> 'I'", nativeQuery = true)
    void sincronizaLojaKunden01();
    

    @Modifying
    @Query(value = "UPDATE "
    		+ "( "
    		+ "SELECT l.loj_codigo_interno, k.nome_fantasia  "
    		+ "FROM LOJA l "
    		+ "JOIN kunden.cliente_planet_v@DBL_KUNDEN k ON k.cnpj_cpf = l.LOJ_CNPJ "
    		+ "WHERE l.LOJ_CODIGO_INTERNO  <> k.nome_fantasia and l.LOJ_STATUS<>'I'"
    		+ ") "
    		+ "SET LOJ_CODIGO_INTERNO  = nome_fantasia ", nativeQuery = true)
    void sincronizaLojaKunden02();

    @Modifying
    @Query(value = "UPDATE "
    		+ "("
    		+ "SELECT l.loj_status, CASE WHEN SITUACAO_COMERCIAL='L' THEN 'A' WHEN SITUACAO_COMERCIAL='B' THEN 'B' END kunden_status "
    		+ "from loja l "
    		+ "JOIN kunden.cliente_planet_v@DBL_KUNDEN k ON k.cnpj_cpf = l.LOJ_CNPJ "
    		+ "WHERE l.loj_status  <> CASE WHEN SITUACAO_COMERCIAL='L' THEN 'A' WHEN SITUACAO_COMERCIAL='B' THEN 'B' END "
    		+ "and l.LOJ_STATUS<>'I') "
    		+ "SET LOJ_STATUS  = KUNDEN_STATUS ", nativeQuery = true)
    void sincronizaLojaKunden03();

    @Modifying
    @Query(value = "INSERT INTO LOJA "
    		+ "(LOJ_ID,LOJ_NOME, LOJ_CNPJ, LOJ_CODIGO_INTERNO, FRQ_ID, LOJ_STATUS) "
    		+ "SELECT SQ_LOJA.nextval,  RAZAO_SOCIAL, CNPJ_CPF, NOME_FANTASIA,  -1, CASE WHEN SITUACAO_COMERCIAL='L' THEN 'A' WHEN SITUACAO_COMERCIAL='B' THEN 'B' end  "
    		+ "FROM kunden.cliente_planet_v@DBL_KUNDEN k "
    		+ "WHERE NOT EXISTS (SELECT 1 FROM LOJA l  WHERE l.LOJ_CNPJ = k.CNPJ_CPF) "
    		+ "AND nome_fantasia IS NOT NULL AND length(nome_fantasia)<6", nativeQuery = true)
    void sincronizaLojaKunden04();

    
}
