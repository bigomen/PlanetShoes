package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.model.Uf;
import com.luxfacta.planetshoes.api.repository.UfRepository;
import com.luxfacta.planetshoes.api.rest.RestUf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UfController extends AuthenticatedController {

	@Autowired
	private UfRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/uf/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestUf value) throws BusinessRuleException,BusinessSecurityException {
        Uf dbo = (Uf) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/uf/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestUf value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Uf> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/uf/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Uf> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/uf/recupera/{id}")
	public ResponseEntity<RestUf> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<Uf> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestUf rest = (RestUf) mapper.copyToRestObject(dbo.get(), RestUf.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}
    */
	@GetMapping(value = "admin/v1/uf/lista")
	public ResponseEntity<List<RestUf>> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<Uf> itDbo = repository.findAll(pagination.queryWithoutPagination(Uf.class));
        List<RestUf> restList =  (List<RestUf>) mapper.copyToRestObject(itDbo, RestUf.class);
        
        return new ResponseEntity<>(restList, HttpStatus.OK);
	}
	
}
