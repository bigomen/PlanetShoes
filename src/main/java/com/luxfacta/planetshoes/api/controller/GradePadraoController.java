package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.GradePadrao;
import com.luxfacta.planetshoes.api.repository.*;
import com.luxfacta.planetshoes.api.rest.RestGradePadrao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class GradePadraoController extends AuthenticatedController {

	@Autowired
	private GradePadraoRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private TipoArtigoRepository tipoArtigoRepository;

    @Autowired
    private SemanaRepository semanaRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private CalculoRepository calculoRepository;
    

	@Transactional
	@PostMapping(value = "admin/v1/gradepadrao/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestGradePadrao value) throws BusinessRuleException,BusinessSecurityException {
        GradePadrao dbo = (GradePadrao) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/gradepadrao/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestGradePadrao value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<GradePadrao> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/gradepadrao/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<GradePadrao> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}

    
	@GetMapping(value = "admin/v1/gradepadrao/recupera/{id}")
	public ResponseEntity<RestGradePadrao> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<GradePadrao> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestGradePadrao rest = (RestGradePadrao) mapper.copyToRestObject(dbo.get(), RestGradePadrao.class);

            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	/*
	@GetMapping(value = "admin/v1/gradepadrao/lista/{pagina}/{loja}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String loja) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        Iterable<GradePadrao> itDbo = repository.findByLojId(pagination.gerenateWithPages(pagina, GradePadrao.class), Mapper.decryptId(loja));
        List<RestGradePadrao> restList =  (List<RestGradePadrao>) mapper.copyToRestObject(itDbo, RestGradePadrao.class);
        
        return new ResponseEntity<>(pagination.withPagination(pagina, repository.countRows(), restList), HttpStatus.OK);
	}

    @Transactional
    @GetMapping(value = "admin/v1/gradepadrao/calculo/{semana}/{artigo}/{cor}/{loja}")
    public ResponseEntity<List<RestGradePadrao>> calculo(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor, @PathVariable String loja) throws BusinessSecurityException, NotFoundException {
        Long semanaId = Mapper.decryptId(semana);
        Long artigoId = Mapper.decryptId(artigo);
        Long corId = Mapper.decryptId(cor);
        Long lojaId = Mapper.decryptId(loja);
        if(!semanaRepository.existsById(semanaId) || !artigoRepository.existsById(artigoId) || !corRepository.existsById(corId) || lojaRepository.existsById(lojaId))
            throw new NotFoundException();
        Optional<Calculo> calculo = calculoRepository.findBySemIdDeAndLojId(semanaId, lojaId);
        if(calculo.isEmpty()) throw new NotFoundException();
        List<GradePadrao> grades = repository.findAllByLojIdAndCalIdAndCorIdAndArtId(lojaId, calculo.get().getId(), corId, artigoId);
        List<RestGradePadrao> restList = (List<RestGradePadrao>) mapper.copyToRestObject(grades, RestGradePadrao.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }
	*/
}
