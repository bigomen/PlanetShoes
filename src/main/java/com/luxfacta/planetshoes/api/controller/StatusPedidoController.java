package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.model.StatusPedido;
import com.luxfacta.planetshoes.api.repository.StatusPedidoRepository;
import com.luxfacta.planetshoes.api.rest.RestStatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StatusPedidoController extends AuthenticatedController {

	@Autowired
	private StatusPedidoRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/statuspedido/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestStatusPedido value) throws BusinessRuleException,BusinessSecurityException {
        StatusPedido dbo = (StatusPedido) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/statuspedido/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestStatusPedido value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<StatusPedido> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/statuspedido/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<StatusPedido> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/statuspedido/recupera/{id}")
	public ResponseEntity<RestStatusPedido> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<StatusPedido> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestStatusPedido rest = (RestStatusPedido) mapper.copyToRestObject(dbo.get(), RestStatusPedido.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}
    */
    
	@GetMapping(value = "admin/v1/statuspedido/lista")
	public ResponseEntity<List<RestStatusPedido>> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<StatusPedido> itDbo = repository.findAll();
        List<RestStatusPedido> restList =  (List<RestStatusPedido>) mapper.copyToRestObject(itDbo, RestStatusPedido.class);
        
        return new ResponseEntity<>(restList, HttpStatus.OK);
	}
	
}
