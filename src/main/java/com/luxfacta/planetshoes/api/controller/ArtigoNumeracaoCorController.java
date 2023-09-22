package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Artigo;
import com.luxfacta.planetshoes.api.model.ArtigoNumeracaoCor;
import com.luxfacta.planetshoes.api.repository.ArtigoNumeracaoCorRepository;
import com.luxfacta.planetshoes.api.repository.ArtigoRepository;
import com.luxfacta.planetshoes.api.repository.CorRepository;
import com.luxfacta.planetshoes.api.repository.TipoArtigoRepository;
import com.luxfacta.planetshoes.api.rest.RestArtigoNumeracaoCor;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;

@RestController
public class ArtigoNumeracaoCorController extends AuthenticatedController {

    @Autowired
    private ArtigoNumeracaoCorRepository repository;

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private TipoArtigoRepository tipoArtigoRepository;

    private ArtigoNumeracaoCor validar(ArtigoNumeracaoCor artigoNumeracaoCor, String familia) throws BusinessRuleException {
        if(!corRepository.existsById(artigoNumeracaoCor.getCorId())){
            throw new BusinessRuleException("Cor não encontrada");
        }
        if(!tipoArtigoRepository.existsById(artigoNumeracaoCor.getTarId())){
            throw new BusinessRuleException("Tipo artigo não encontrada");
        }
        Optional<Artigo> artigo = artigoRepository.findByArtDescricaoIgnoreCase(familia);
        if(artigo.isEmpty()) throw new BusinessRuleException("Familia não encontrada"); else artigoNumeracaoCor.setArtId(artigo.get().getId());
        return artigoNumeracaoCor;
    }

    /*
    @Transactional
    @PostMapping(value = "admin/v1/artigoNumeracaoCor/novo")
    public ResponseEntity<Integer> novo(@RequestBody @Valid RestArtigoNumeracaoCor value) throws BusinessRuleException, NotFoundException, BusinessSecurityException{
        ArtigoNumeracaoCor dbo = (ArtigoNumeracaoCor) mapper.copyToDbObject(value);
        validar(dbo, value.getAncFamilia());
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    */

    @Transactional(propagation = Propagation.REQUIRED)
    @PutMapping(value = "admin/v1/artigoNumeracaoCor/atualiza")
    public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestArtigoNumeracaoCor value) throws BusinessRuleException, NotFoundException, BusinessSecurityException{
        if(Objects.nonNull(value.getId())){
            Optional<ArtigoNumeracaoCor> dbo = repository.findById(Mapper.decryptId(value.getId()));

            if(dbo.isPresent()) {
            	
            	dbo.get().setAncGrade(value.getAncGrade());
            	dbo.get().setTarId(Mapper.decryptId(value.getTarId()));
            	
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        throw new NotFoundException();
    }

    @GetMapping(value = "admin/v1/artigoNumeracaoCor/recupera/{id}")
    public ResponseEntity<RestArtigoNumeracaoCor> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<ArtigoNumeracaoCor> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            mapper.setMaxLevel(3);
            RestArtigoNumeracaoCor rest = (RestArtigoNumeracaoCor) mapper.copyToRestObject(dbo.get(), RestArtigoNumeracaoCor.class);

            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
        throw new NotFoundException();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @DeleteMapping(value = "admin/v1/artigoNumeracaoCor/remove/{id}")
    public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<ArtigoNumeracaoCor> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {

                repository.delete(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        throw new NotFoundException();
    }

    @GetMapping(value = "admin/v1/artigoNumeracaoCor/lista/{pagina}")
    public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(3);
        
        String famGeraPedido = allParams.getOrDefault("artigo$familia$famGeraPedido", null);
        String famMarca = allParams.getOrDefault("artigo$familia$famMarca", null);
		String artDescricao  = allParams.getOrDefault("artigo$artDescricao", null);
		String corDescricao  = allParams.getOrDefault("cor$corDescricao", null);
		Integer ancNumeracao  = "".equals(allParams.getOrDefault("ancNumeracao","")) ? null : Integer.valueOf(allParams.getOrDefault("ancNumeracao", null));
		
		if (famMarca != null) famMarca = "%" + famMarca + "%";
		if (artDescricao != null) artDescricao = "%" + artDescricao + "%";
		if (corDescricao != null) corDescricao = "%" + corDescricao + "%";
		if ("".equals(famGeraPedido)) famGeraPedido = null;
		

        Page<ArtigoNumeracaoCor> itDbo = repository.listaArtigos(pagination.queryWithPagination(pagina, ArtigoNumeracaoCor.class), famMarca, artDescricao, corDescricao, ancNumeracao, famGeraPedido);
        List<RestArtigoNumeracaoCor> restList =  (List<RestArtigoNumeracaoCor>) mapper.copyToRestObject(itDbo.getContent(), RestArtigoNumeracaoCor.class);

        return new ResponseEntity<>(pagination.toResponse(pagina, itDbo.getTotalPages(), restList), HttpStatus.OK);
    }
}
