package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Calculo;
import com.luxfacta.planetshoes.api.model.VendaProjetada;
import com.luxfacta.planetshoes.api.repository.*;
import com.luxfacta.planetshoes.api.rest.RestVendaProjetada;
import com.luxfacta.planetshoes.api.shared.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class VendaProjetadaController extends AuthenticatedController {

	@Autowired
	private VendaProjetadaRepository repository;

    @Autowired
    private SemanaRepository semanaRepository;

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private CorRepository corRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private CalculoRepository calculoRepository;

    /*
	@Transactional
	@PostMapping(value = "admin/v1/vendaprojetada/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestVendaProjetada value) throws BusinessRuleException,BusinessSecurityException {
        VendaProjetada dbo = (VendaProjetada) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/vendaprojetada/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestVendaProjetada value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<VendaProjetada> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/vendaprojetada/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<VendaProjetada> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/vendaprojetada/recupera/{id}")
	public ResponseEntity<RestVendaProjetada> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<VendaProjetada> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestVendaProjetada rest = (RestVendaProjetada) mapper.copyToRestObject(dbo.get(), RestVendaProjetada.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/vendaprojetada/lista/{calculo}")
	public ResponseEntity<Pagination> lista(@PathVariable String calculo) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        List<VendaProjetada> itDbo = repository.findByCalId(pagination.queryWithoutPagination(VendaProjetada.class), Mapper.decryptId(calculo));
        List<RestVendaProjetada> restList =  (List<RestVendaProjetada>) mapper.copyToRestObject(itDbo, RestVendaProjetada.class);
        
        return new ResponseEntity<>(pagination.toResponse(repository.countRows(), restList), HttpStatus.OK);
	}

    @Transactional
    @GetMapping(value = "admin/v1/vendaprojetada/calculo/{semana}/{artigo}/{cor}/{loja}")
    public ResponseEntity<List<RestVendaProjetada>> consultaCalculo(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor, @PathVariable String loja) throws BusinessSecurityException, NotFoundException {
        Long semanaId = Mapper.decryptId(semana);
        Long artigoId = Mapper.decryptId(artigo);
        Long corId = Mapper.decryptId(cor);
        Long lojaId = Mapper.decryptId(loja);
        Optional<Calculo> calculo = calculoRepository.findBySemIdDeAndLojId(semanaId, lojaId);
        if (calculo.isPresent()) {
	        List<VendaProjetada> vendasProjetadas = repository.findAllByCorIdAndArtIdAndCalIdOrderById(corId, artigoId, calculo.get().getId());
	        List<RestVendaProjetada> restList = mapper.copyToRestObject(vendasProjetadas, RestVendaProjetada.class);
	        return new ResponseEntity<>(restList, HttpStatus.OK);
        }
		throw new NotFoundException();
    }
	
}
