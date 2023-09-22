package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.TipoLoja;
import com.luxfacta.planetshoes.api.repository.TipoLojaRepository;
import com.luxfacta.planetshoes.api.rest.RestTipoLoja;
import com.luxfacta.planetshoes.api.shared.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class TipoLojaController extends AuthenticatedController {

	@Autowired
	private TipoLojaRepository repository;

	@Transactional
	@PostMapping(value = "admin/v1/tipoloja/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestTipoLoja value) throws BusinessRuleException,BusinessSecurityException {
		TipoLoja dbo = (TipoLoja) mapper.copyToDbObject(value);
		if(repository.existsByTloDescricao(value.getTloDescricao())) throw new BusinessRuleException("Descrição já existe!");
		repository.save(dbo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/tipoloja/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestTipoLoja value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<TipoLoja> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                if(repository.validarDescricao(value.getTloDescricao(), dbo.get().getId())) throw new BusinessRuleException("Descrição usada por outro tipo");
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/tipoloja/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<TipoLoja> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/tipoloja/recupera/{id}")
	public ResponseEntity<RestTipoLoja> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<TipoLoja> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestTipoLoja rest = (RestTipoLoja) mapper.copyToRestObject(dbo.get(), RestTipoLoja.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

    
	@GetMapping(value = "admin/v1/tipoloja/lista")
	public ResponseEntity<Pagination> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<TipoLoja> itDbo = repository.findAll(pagination.queryWithoutPagination(TipoLoja.class));
        List<RestTipoLoja> restList =  (List<RestTipoLoja>) mapper.copyToRestObject(itDbo, RestTipoLoja.class);
        
        return new ResponseEntity<>(pagination.toResponse(restList.size(), restList), HttpStatus.OK);
	}

	@GetMapping(value = "admin/v1/tipoloja/listaSimples")
	public ResponseEntity<List<RestTipoLoja>> listaSimples() throws BusinessSecurityException {
		List<TipoLoja> tipoLojas = repository.listaSimples();
		List<RestTipoLoja> restTipoLojas = (List<RestTipoLoja>) mapper.copyToRestObject(tipoLojas, RestTipoLoja.class);
		return new ResponseEntity<>(restTipoLojas, HttpStatus.OK);
	}
}
