package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Sazonalidade;
import com.luxfacta.planetshoes.api.repository.LojaRepository;
import com.luxfacta.planetshoes.api.repository.SazonalidadeRepository;
import com.luxfacta.planetshoes.api.repository.SemanaRepository;
import com.luxfacta.planetshoes.api.rest.RestSazonalidade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class SazonalidadeController extends AuthenticatedController {

	@Autowired
	private SazonalidadeRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private SemanaRepository semanaRepository;

    private void validar(Sazonalidade sazonalidade) throws BusinessRuleException {
        if(!lojaRepository.existsById(sazonalidade.getLojId())) {
            throw new BusinessRuleException("Loja não encontrada");
        }
        if(!semanaRepository.existsById(sazonalidade.getSemId())){
            throw new BusinessRuleException("Semana não encontrada");
        }
    }

	@Transactional
	@PostMapping(value = "admin/v1/sazonalidade/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestSazonalidade value) throws BusinessRuleException,BusinessSecurityException {
        Sazonalidade dbo = (Sazonalidade) mapper.copyToDbObject(value);
        validar(dbo);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/sazonalidade/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestSazonalidade value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Sazonalidade> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                validar(dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/sazonalidade/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Sazonalidade> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    
	@GetMapping(value = "admin/v1/sazonalidade/recupera/{id}")
	public ResponseEntity<RestSazonalidade> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Sazonalidade> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestSazonalidade rest = (RestSazonalidade) mapper.copyToRestObject(dbo.get(), RestSazonalidade.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	/*
	@GetMapping(value = {"admin/v1/sazonalidade/lista/{loja}", "admin/v1/sazonalidade/lista/{pagina}/{loja}/{semana}"})
	public ResponseEntity<Pagination> lista(@PathVariable(required = false) Long pagina, @PathVariable String loja, @PathVariable(required = false) String semana) throws BusinessSecurityException {
        Iterable<Sazonalidade> itDbo;
        mapper.setMaxLevel(1);
        if(semana != null){
            mapper.setMaxLevel(2);
            Pageable pageable = PageRequest.of(Math.toIntExact(pagina), 25);
            itDbo = repository.findByLojIdAndSemId(pageable, Mapper.decryptId(loja), Mapper.decryptId(semana));
        }else {
            itDbo = repository.listaPorSemana(Mapper.decryptId(loja));
        }
        List<RestSazonalidade> restList = new ArrayList<>();
        RestSazonalidade restSazonalidade;
        for (Sazonalidade s : itDbo){
            restSazonalidade = (RestSazonalidade) mapper.copyToRestObject(s, RestSazonalidade.class);
            restSazonalidade.setSemana((RestSemana) mapper.copyToRestObject(s.getSemana(), RestSemana.class));
            restList.add(restSazonalidade);
        }
        return new ResponseEntity<>(pagination.withNoPagination(restList.size(), restList), HttpStatus.OK);
	}
	*/
	
}
