package com.luxfacta.planetshoes.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luxfacta.planetshoes.api.model.Sazonalidade;



@Repository
public interface SazonalidadeRepository extends CrudRepository<Sazonalidade,Long> {
   
    public Page<Sazonalidade> findAll(Pageable pageable);
    
    
    @Query(value = "SELECT SAZ_ID, SAZ_PORCENTAGEM_AJUSTE, SEM_ID, LOJ_ID, SAZ_PORCENTAGEM_ORIGINAL  "
    		+ "FROM SAZONALIDADE WHERE LOJ_ID =:lojId AND SEM_ID = :semId "
    		+ "UNION ALL "
    		+ "SELECT SRZ_ID, SRZ_PORCENTAGEM_AJUSTE, SEM_ID, LOJ_ID, SRZ_PORCENTAGEM_ORIGINAL "
    		+ "FROM SAZONALIDADE_REDE R "
    		+ "JOIN FRANQUEADO F  ON R.RED_ID = F.RED_ID "
    		+ "JOIN LOJA L ON L.FRQ_ID = F.FRQ_ID "
    		+ "WHERE NOT EXISTS (SELECT 1 FROM SAZONALIDADE S WHERE S.LOJ_ID=L.LOJ_ID) AND "
    		+ "L.LOJ_ID =:lojId AND SEM_ID = :semId  ", nativeQuery = true)
    public Optional<Sazonalidade> findByLojIdAndSemId( Long lojId, Long semId);

    @Query("from Sazonalidade s join s.semana se " +
            "where s.lojId = :lojId order by se.semNumero")
    List<Sazonalidade> listaPorSemana(Long lojId);
    
    @Modifying
    @Query(value = "DELETE SAZONALIDADE WHERE SEM_ID IN (SELECT SEM_ANO FROM SEMANA WHERE SEM_ANO=:ano)", 
    	   nativeQuery=true)
    public int deleteByAno(@Param("ano") int ano);

    @Modifying
    @Query(value = "DELETE SAZONALIDADE_REDE WHERE SEM_ID IN (SELECT SEM_ANO FROM SEMANA WHERE SEM_ANO=:ano)", 
    	   nativeQuery=true)
    public int deleteRedeByAno(@Param("ano") int ano);

    @Modifying
    @Query(value = "INSERT INTO SAZONALIDADE (SAZ_ID, SAZ_PORCENTAGEM_AJUSTE, SEM_ID, LOJ_ID, SAZ_PORCENTAGEM_ORIGINAL) "
    			 + "SELECT SQ_SAZONALIDADE.NEXTVAL, PCT, SEM_ID, LOJ_ID, PCT FROM VW_LOJA_SAZONALIDADE where SEM_ANO = :ano", 
    	   nativeQuery=true)
    public int processaMovimentacao(@Param("ano") int ano);
    
    @Modifying
    @Query(value = "INSERT INTO SAZONALIDADE_REDE (SRZ_ID, SRZ_PORCENTAGEM_AJUSTE, SEM_ID, LOJ_ID, SRZ_PORCENTAGEM_ORIGINAL) "
    			 + "SELECT SQ_SAZONALIDADE_REDE.NEXTVAL, PCT, SEM_ID, LOJ_ID, PCT FROM VW_REDE_SAZONALIDADE where SEM_ANO = :ano", 
    	   nativeQuery=true)
    public int processaMovimentacaoRede(@Param("ano") int ano);

}
