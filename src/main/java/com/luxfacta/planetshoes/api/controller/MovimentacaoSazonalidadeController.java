package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Calculo;
import com.luxfacta.planetshoes.api.model.MovimentacaoSazonalidade;
import com.luxfacta.planetshoes.api.repository.CalculoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoSazonalidadeRepository;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoSazonalidade;
import com.luxfacta.planetshoes.api.shared.Pagination;


@RestController
public class MovimentacaoSazonalidadeController extends AuthenticatedController {

	@Autowired
	private MovimentacaoSazonalidadeRepository repository;

	@Autowired
	private CalculoRepository calculoRepository;

    /*
	@Transactional
	@PostMapping(value = "admin/v1/movimentacaosazonalidade/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestMovimentacaoSazonalidade value) throws BusinessRuleException,BusinessSecurityException {
        MovimentacaoSazonalidade dbo = (MovimentacaoSazonalidade) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/movimentacaosazonalidade/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestMovimentacaoSazonalidade value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<MovimentacaoSazonalidade> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/movimentacaosazonalidade/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<MovimentacaoSazonalidade> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/movimentacaosazonalidade/recupera/{id}")
	public ResponseEntity<RestMovimentacaoSazonalidade> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<MovimentacaoSazonalidade> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestMovimentacaoSazonalidade rest = mapper.copyToRestObject(dbo.get(), RestMovimentacaoSazonalidade.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

    @Transactional
    @GetMapping(value = "admin/v1/movimentacaosazonalidade/calculo/{semana}/{artigo}/{cor}/{loja}")
    public ResponseEntity<List<RestMovimentacaoSazonalidade>> consultaCalculo(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor, @PathVariable String loja) throws BusinessSecurityException, NotFoundException {
        Long semanaId = Mapper.decryptId(semana);
        Long artigoId = Mapper.decryptId(artigo);
        Long corId = Mapper.decryptId(cor);
        Long lojaId = Mapper.decryptId(loja);
        Optional<Calculo> calculo = calculoRepository.findBySemIdDeAndLojId(semanaId, lojaId);
        if (calculo.isPresent()) {
	        Iterable<MovimentacaoSazonalidade> grades = repository.findByArtIdAndCorIdAndCalIdOrderById(artigoId, corId, calculo.get().getId());
	        List<RestMovimentacaoSazonalidade> restList = mapper.copyToRestObject(grades, RestMovimentacaoSazonalidade.class);
	        return new ResponseEntity<>(restList, HttpStatus.OK);
        }
		throw new NotFoundException();
        
    }
	
	@GetMapping(value = "admin/v1/movimentacaosazonalidade/lista/{pagina}/{semana}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String semana) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        Iterable<MovimentacaoSazonalidade> itDbo = repository.findBySemId(pagination.queryWithPagination(pagina, MovimentacaoSazonalidade.class), Mapper.decryptId(semana));
        List<RestMovimentacaoSazonalidade> restList =  (List<RestMovimentacaoSazonalidade>) mapper.copyToRestObject(itDbo, RestMovimentacaoSazonalidade.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}
}
