package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.SolicitacaoPedido;
import com.luxfacta.planetshoes.api.repository.SolicitacaoPedidoRepository;
import com.luxfacta.planetshoes.api.rest.RestSolicitacaoPedido;
import com.luxfacta.planetshoes.api.shared.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class SolicitacaoPedidoController extends AuthenticatedController {

	@Autowired
	private SolicitacaoPedidoRepository repository;
    
	@Transactional
	@PostMapping(value = "admin/v1/solicitacaopedido/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestSolicitacaoPedido value) throws BusinessRuleException,BusinessSecurityException {
        SolicitacaoPedido dbo = (SolicitacaoPedido) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/solicitacaopedido/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestSolicitacaoPedido value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<SolicitacaoPedido> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/solicitacaopedido/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<SolicitacaoPedido> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/solicitacaopedido/recupera/{id}")
	public ResponseEntity<RestSolicitacaoPedido> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<SolicitacaoPedido> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestSolicitacaoPedido rest = (RestSolicitacaoPedido) mapper.copyToRestObject(dbo.get(), RestSolicitacaoPedido.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/solicitacaopedido/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<SolicitacaoPedido> itDbo = repository.findAll(pagination.queryWithPagination(pagina, SolicitacaoPedido.class));
        List<RestSolicitacaoPedido> restList =  (List<RestSolicitacaoPedido>) mapper.copyToRestObject(itDbo, RestSolicitacaoPedido.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}
	
}
