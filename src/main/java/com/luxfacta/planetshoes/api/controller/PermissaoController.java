package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.model.Permissao;
import com.luxfacta.planetshoes.api.repository.PermissaoRepository;
import com.luxfacta.planetshoes.api.rest.RestPermissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class PermissaoController extends AuthenticatedController {

	@Autowired
	private PermissaoRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/permissao/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestPermissao value) throws BusinessRuleException,BusinessSecurityException {
        Permissao dbo = (Permissao) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/permissao/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestPermissao value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Permissao> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/permissao/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Permissao> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/permissao/recupera/{id}")
	public ResponseEntity<RestPermissao> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<Permissao> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestPermissao rest = (RestPermissao) mapper.copyToRestObject(dbo.get(), RestPermissao.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}
    */
    
	@GetMapping(value = "admin/v1/permissao/lista")
	public ResponseEntity<List<RestPermissao>> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<Permissao> itDbo = repository.findAll(pagination.queryWithoutPagination(Permissao.class));
        List<RestPermissao> restList =  (List<RestPermissao>) mapper.copyToRestObject(itDbo, RestPermissao.class);
        
        return new ResponseEntity<>(restList, HttpStatus.OK);
	}
	
}
