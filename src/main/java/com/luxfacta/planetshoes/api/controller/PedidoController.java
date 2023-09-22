package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Pedido;
import com.luxfacta.planetshoes.api.repository.PedidoRepository;
import com.luxfacta.planetshoes.api.rest.RestPedido;
import com.luxfacta.planetshoes.api.shared.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class PedidoController extends AuthenticatedController {

	@Autowired
	private PedidoRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/pedido/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestPedido value) throws BusinessRuleException,BusinessSecurityException {
        Pedido dbo = (Pedido) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/pedido/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestPedido value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Pedido> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/pedido/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Pedido> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/pedido/recupera/{id}")
	public ResponseEntity<RestPedido> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<Pedido> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestPedido rest = (RestPedido) mapper.copyToRestObject(dbo.get(), RestPedido.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/pedido/lista/{pagina}/{loja}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String loja) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<Pedido> itDbo = repository.findByLojId(pagination.queryWithPagination(pagina, Pedido.class), Mapper.decryptId(loja));
        List<RestPedido> restList =  (List<RestPedido>) mapper.copyToRestObject(itDbo, RestPedido.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}
	
}
