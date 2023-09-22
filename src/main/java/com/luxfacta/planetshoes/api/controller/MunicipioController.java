package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.model.Municipio;
import com.luxfacta.planetshoes.api.repository.MunicipioRepository;
import com.luxfacta.planetshoes.api.rest.RestMunicipio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MunicipioController extends AuthenticatedController {

	@Autowired
	private MunicipioRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/municipio/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestMunicipio value) throws BusinessRuleException,BusinessSecurityException {
        Municipio dbo = (Municipio) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/municipio/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestMunicipio value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Municipio> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/municipio/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Municipio> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/municipio/recupera/{id}")
	public ResponseEntity<RestMunicipio> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<Municipio> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestMunicipio rest = (RestMunicipio) mapper.copyToRestObject(dbo.get(), RestMunicipio.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}
    */
    
	@GetMapping(value = "admin/v1/municipio/lista/{uf}")
	public ResponseEntity<List<RestMunicipio>> lista(@PathVariable String uf) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        
        Iterable<Municipio> itDbo = repository.findByUfIdOrderByMunNome(Mapper.decryptId(uf));
        List<RestMunicipio> restList =  (List<RestMunicipio>) mapper.copyToRestObject(itDbo, RestMunicipio.class);
        
        return new ResponseEntity<>(restList, HttpStatus.OK);
	}
	
}
