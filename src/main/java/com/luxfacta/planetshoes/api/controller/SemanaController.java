package com.luxfacta.planetshoes.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.luxfacta.planetshoes.api.model.Semana;
import com.luxfacta.planetshoes.api.repository.SemanaRepository;
import com.luxfacta.planetshoes.api.rest.RestSemana;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class SemanaController extends AuthenticatedController {

	@Autowired
	private SemanaRepository repository;
    
	@Transactional
	@PostMapping(value = "admin/v1/semana/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestSemana value) throws BusinessRuleException,BusinessSecurityException {
        Semana dbo = (Semana) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/semana/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestSemana value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Semana> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/semana/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Semana> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/semana/recupera/{id}")
	public ResponseEntity<RestSemana> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Semana> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestSemana rest = (RestSemana) mapper.copyToRestObject(dbo.get(), RestSemana.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/semana/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date data = null;
        try {
        	if (allParams.getOrDefault("data", null) != null && !"".equals(allParams.getOrDefault("data", null)))
        		data = formatter.parse(allParams.get("data"));
		} catch (ParseException e) {
			
		}

        
		Long semAno = allParams.getOrDefault("semAno", null) == null || "".equals(allParams.getOrDefault("semAno", null)) ? null : Long.valueOf(allParams.getOrDefault("semAno", null));
		Long semNumero = allParams.getOrDefault("semNumero", null) == null || "".equals(allParams.getOrDefault("semNumero", null)) ? null : Long.valueOf(allParams.getOrDefault("semNumero", null));

        
        Page<Semana> itDbo = repository.listaSemanas(pagination.queryWithPagination(pagina, Semana.class), semAno, semNumero, data );
        List<RestSemana> restList =  (List<RestSemana>) mapper.copyToRestObject(itDbo.getContent(), RestSemana.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, itDbo.getTotalPages(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/semana/listaSimples")
    public ResponseEntity<List<RestSemana>> listaSimples() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        List<Semana> semanas = repository.listaSimples();
        List<RestSemana> restSemanas =  mapper.copyToRestObject(semanas, RestSemana.class);
        return new ResponseEntity<>(restSemanas, HttpStatus.OK);
    }
}
