package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Regiao;
import com.luxfacta.planetshoes.api.repository.RegiaoRepository;
import com.luxfacta.planetshoes.api.rest.RestRegiao;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class RegiaoController extends AuthenticatedController {

	@Autowired
	private RegiaoRepository repository;

    
	@Transactional
	@PostMapping(value = "admin/v1/regiao/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestRegiao value) throws BusinessRuleException,BusinessSecurityException {
        Regiao dbo = (Regiao) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/regiao/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestRegiao value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Regiao> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/regiao/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Regiao> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/regiao/recupera/{id}")
	public ResponseEntity<RestRegiao> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Regiao> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            mapper.setMaxLevel(2);
            RestRegiao rest = (RestRegiao) mapper.copyToRestObject(dbo.get(), RestRegiao.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/regiao/lista")
	public ResponseEntity<Pagination> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        Iterable<Regiao> itDbo = repository.findAll(pagination.queryWithoutPagination(Regiao.class));
        List<RestRegiao> restList =  (List<RestRegiao>) mapper.copyToRestObject(itDbo, RestRegiao.class);
        
        return new ResponseEntity<>(pagination.toResponse(restList.size(), restList), HttpStatus.OK);
	}
	
}
