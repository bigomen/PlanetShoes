package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.TipoArtigo;
import com.luxfacta.planetshoes.api.repository.TipoArtigoRepository;
import com.luxfacta.planetshoes.api.rest.RestTipoArtigo;
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
public class TipoArtigoController extends AuthenticatedController {

	@Autowired
	private TipoArtigoRepository repository;
    
	@Transactional
	@PostMapping(value = "admin/v1/tipoartigo/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestTipoArtigo value) throws BusinessRuleException,BusinessSecurityException {
        TipoArtigo dbo = (TipoArtigo) mapper.copyToDbObject(value);
        if(repository.existsByTarDescricao(dbo.getTarDescricao())) throw new BusinessRuleException("Descrição já existe!");
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/tipoartigo/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestTipoArtigo value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<TipoArtigo> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                if(repository.validarDescricao(value.getTarDescricao(), dbo.get().getId())) throw new BusinessRuleException("Descricao já utilziada por outro tipo");
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/tipoartigo/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<TipoArtigo> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/tipoartigo/recupera/{id}")
	public ResponseEntity<RestTipoArtigo> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<TipoArtigo> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestTipoArtigo rest = (RestTipoArtigo) mapper.copyToRestObject(dbo.get(), RestTipoArtigo.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/tipoartigo/lista")
	public ResponseEntity<Pagination> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        Iterable<TipoArtigo> itDbo = repository.findAll(pagination.queryWithoutPagination(TipoArtigo.class));
        List<RestTipoArtigo> restList =  (List<RestTipoArtigo>) mapper.copyToRestObject(itDbo, RestTipoArtigo.class);
        
        return new ResponseEntity<>(pagination.toResponse(restList.size(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/tipoartigo/listaSimples")
    public ResponseEntity<List<RestTipoArtigo>> listaSimples() throws BusinessSecurityException {
        List<TipoArtigo> tipoArtigos = repository.listaSimples();
        List<RestTipoArtigo> restTipoArtigos = (List<RestTipoArtigo>) mapper.copyToRestObject(tipoArtigos, RestTipoArtigo.class);
        return new ResponseEntity<>(restTipoArtigos, HttpStatus.OK);
    }
}
