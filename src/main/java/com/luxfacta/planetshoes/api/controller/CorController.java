package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Cor;
import com.luxfacta.planetshoes.api.repository.CorRepository;
import com.luxfacta.planetshoes.api.rest.RestCor;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class CorController extends AuthenticatedController {

	@Autowired
	private CorRepository repository;

   
	@Transactional
	@PostMapping(value = "admin/v1/cor/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestCor value) throws BusinessRuleException,BusinessSecurityException {
        Cor dbo = (Cor) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/cor/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestCor value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Cor> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/cor/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Cor> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
    
	@GetMapping(value = "admin/v1/cor/recupera/{id}")
	public ResponseEntity<RestCor> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Cor> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            mapper.setMaxLevel(2);
            RestCor rest = (RestCor) mapper.copyToRestObject(dbo.get(), RestCor.class);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/cor/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        
		String corDescricao = allParams.getOrDefault("corDescricao", null);
		String corCodigoErp  = allParams.getOrDefault("corCodigoErp", null);

		if (corDescricao != null) corDescricao = "%" + corDescricao + "%";

        Page<Cor> itDbo = repository.listaCores(pagination.queryWithPagination(pagina, Cor.class), corDescricao, corCodigoErp);
        List<RestCor> restList =  (List<RestCor>) mapper.copyToRestObject(itDbo, RestCor.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, itDbo.getTotalPages(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/cor/listaSimples")
    public ResponseEntity<List<RestCor>> listaSimples() throws BusinessSecurityException {
        List<Cor> cors = repository.listaSimples();
        List<RestCor> restCors = (List<RestCor>) mapper.copyToRestObject(cors, RestCor.class);
        return new ResponseEntity<>(restCors, HttpStatus.OK);
    }

}
