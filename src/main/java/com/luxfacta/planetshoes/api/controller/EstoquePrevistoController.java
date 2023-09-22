package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.EstoquePrevisto;
import com.luxfacta.planetshoes.api.repository.EstoquePrevistoRepository;
import com.luxfacta.planetshoes.api.rest.RestEstoquePrevisto;
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
public class EstoquePrevistoController extends AuthenticatedController {

	@Autowired
	private EstoquePrevistoRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/estoqueprevisto/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestEstoquePrevisto value) throws BusinessRuleException,BusinessSecurityException {
        EstoquePrevisto dbo = (EstoquePrevisto) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/estoqueprevisto/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestEstoquePrevisto value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<EstoquePrevisto> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/estoqueprevisto/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<EstoquePrevisto> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/estoqueprevisto/recupera/{id}")
	public ResponseEntity<RestEstoquePrevisto> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<EstoquePrevisto> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestEstoquePrevisto rest = (RestEstoquePrevisto) mapper.copyToRestObject(dbo.get(), RestEstoquePrevisto.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/estoqueprevisto/lista/{pagina}/{calculo}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String calculo) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<EstoquePrevisto> itDbo = repository.findByCalId(pagination.queryWithPagination(pagina, EstoquePrevisto.class), Mapper.decryptId(calculo));
        List<RestEstoquePrevisto> restList =  (List<RestEstoquePrevisto>) mapper.copyToRestObject(itDbo, RestEstoquePrevisto.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}
	
}
