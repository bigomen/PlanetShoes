package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.GradeProjetada;
import com.luxfacta.planetshoes.api.repository.GradeProjetadaRepository;
import com.luxfacta.planetshoes.api.rest.RestGradeProjetada;
import com.luxfacta.planetshoes.api.shared.Pagination;


@RestController
public class GradeProjetadaController extends AuthenticatedController {

	@Autowired
	private GradeProjetadaRepository repository;
/*
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

    @Autowired
    private ArtigoNumeracaoCorRepository artigoNumeracaoCorRepository;
*/
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/gradeprojetada/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestGradeProjetada value) throws BusinessRuleException,BusinessSecurityException {
        GradeProjetada dbo = (GradeProjetada) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/gradeprojetada/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestGradeProjetada value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<GradeProjetada> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/gradeprojetada/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<GradeProjetada> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/gradeprojetada/recupera/{id}")
	public ResponseEntity<RestGradeProjetada> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<GradeProjetada> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
            
            RestGradeProjetada rest =  mapper.copyToRestObject(dbo.get(), RestGradeProjetada.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/gradeprojetada/lista/{pagina}/{calculo}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String calculo) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        Iterable<GradeProjetada> itDbo = repository.findByCalId(pagination.queryWithPagination(pagina, GradeProjetada.class), Mapper.decryptId(calculo));
        List<RestGradeProjetada> restList =  mapper.copyToRestObject(itDbo, RestGradeProjetada.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}

    @Transactional
    @GetMapping(value = "admin/v1/gradeprojetada/calculo/{semana}/{artigo}/{cor}/{loja}")
    public ResponseEntity<List<RestGradeProjetada>> consultaCalculo(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor, @PathVariable String loja) throws BusinessSecurityException, NotFoundException {
        Long semanaId = Mapper.decryptId(semana);
        Long artigoId = Mapper.decryptId(artigo);
        Long corId = Mapper.decryptId(cor);
        Long lojaId = Mapper.decryptId(loja);

        Iterable<GradeProjetada> grades = repository.findByArtIdAndCorIdAndSemIdAndLojId(artigoId, corId, semanaId, lojaId);
        List<RestGradeProjetada> restList = mapper.copyToRestObject(grades, RestGradeProjetada.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "admin/v1/gradeprojetada/calculo/atualiza")
    public ResponseEntity<Integer> atualizaCalculo(@RequestBody List<RestGradeProjetada> gradesProjetadas) throws BusinessSecurityException, NotFoundException {
        for(RestGradeProjetada rgp : gradesProjetadas){
            Long gradeId = Mapper.decryptId(rgp.getId());
            if(!repository.existsById(gradeId)) throw new NotFoundException();
            repository.atualizarPedido(rgp.getGprPedidoFabrica(), gradeId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
