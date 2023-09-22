package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Familia;
import com.luxfacta.planetshoes.api.repository.FamiliaRepository;
import com.luxfacta.planetshoes.api.rest.RestFamilia;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class FamiliaController extends AuthenticatedController {

	@Autowired
	private FamiliaRepository repository;


    
	@Transactional
	@PostMapping(value = "admin/v1/familia/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestFamilia value) throws BusinessRuleException,BusinessSecurityException {
        mapper.setMaxLevel(3);
        Familia dbo = (Familia) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/familia/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestFamilia value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Familia> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                //mapper.setMaxLevel(3);
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/familia/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException, BusinessRuleException {
        if (id != null) {
            Optional<Familia> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                try {
                    repository.delete(dbo.get());
                }catch (Exception ex){
                    throw new BusinessRuleException("Familia possui artigos em uso!");
                }

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/familia/recupera/{id}")
	public ResponseEntity<RestFamilia> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Familia> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            mapper.setMaxLevel(2);
            RestFamilia rest = (RestFamilia) mapper.copyToRestObject(dbo.get(), RestFamilia.class);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/familia/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        String famMarca = allParams.getOrDefault("famMarca", null);
		
		
		if (famMarca != null) famMarca = "%" + famMarca + "%";
        
        Page<Familia> itDbo = repository.listaFamilia(pagination.queryWithPagination(pagina, Familia.class),famMarca);
        List<RestFamilia> restList =  (List<RestFamilia>) mapper.copyToRestObject(itDbo.getContent(), RestFamilia.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, itDbo.getTotalPages(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/familia/listaSimples")
    public ResponseEntity<List<RestFamilia>> listaSimples() throws BusinessSecurityException {
        List<Familia> familias = repository.listaSimples();
        List<RestFamilia> restFamilias = (List<RestFamilia>) mapper.copyToRestObject(familias, RestFamilia.class);
        return new ResponseEntity<>(restFamilias, HttpStatus.OK);
    }
}
