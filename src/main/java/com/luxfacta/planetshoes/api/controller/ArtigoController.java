package com.luxfacta.planetshoes.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Artigo;
import com.luxfacta.planetshoes.api.repository.ArtigoRepository;
import com.luxfacta.planetshoes.api.rest.RestArtigo;


@RestController
public class ArtigoController extends AuthenticatedController {

	@Autowired
	private ArtigoRepository repository;

	@GetMapping(value = "admin/v1/artigo/recupera/{id}")
	public ResponseEntity<RestArtigo> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Artigo> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            mapper.setMaxLevel(2);
            RestArtigo rest = mapper.copyToRestObject(dbo.get(), RestArtigo.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	/*

    @Autowired
    private FamiliaRepository familiaRepository;
    
    private void validar(Artigo artigo) throws BusinessRuleException {
        if(!familiaRepository.existsById(artigo.getFamId())) throw new BusinessRuleException("Familia não encontrada");
    }

	@Transactional
	@PostMapping(value = "admin/v1/artigo/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestArtigo value) throws BusinessRuleException,BusinessSecurityException {
        Artigo dbo = (Artigo) mapper.copyToDbObject(value);
        validar(dbo);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}
    
	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/artigo/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestArtigo value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Artigo> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                validar(dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/artigo/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Artigo> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    

	@GetMapping(value = "admin/v1/artigo/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        Iterable<Artigo> itDbo = repository.findAll(pagination.queryWithPagination(pagina, Artigo.class));
        List<RestArtigo> restList =  mapper.copyToRestObject(itDbo, RestArtigo.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina,repository.countRows(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/artigo/listaSimples")
    public ResponseEntity<List<RestArtigo>> listaSimples() throws BusinessSecurityException {
        List<Artigo> artigos = repository.listaSimples();
        List<RestArtigo> restArtigos = (List<RestArtigo>) mapper.copyToRestObject(artigos, RestArtigo.class);
        return new ResponseEntity<>(restArtigos, HttpStatus.OK);
    }
    */
}