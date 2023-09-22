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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Loja;
import com.luxfacta.planetshoes.api.model.Municipio;
import com.luxfacta.planetshoes.api.model.TipoLoja;
import com.luxfacta.planetshoes.api.repository.LojaRepository;
import com.luxfacta.planetshoes.api.repository.MunicipioRepository;
import com.luxfacta.planetshoes.api.repository.TipoLojaRepository;
import com.luxfacta.planetshoes.api.rest.RestLoja;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class LojaController extends AuthenticatedController {

	@Autowired
	private LojaRepository repository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private TipoLojaRepository tipoLojaRepository;


    @Transactional
    private Loja validar(Loja dbo) throws BusinessSecurityException, BusinessRuleException {

        if((dbo.getLojDataFim() != null) && dbo.getLojDataFim().before(dbo.getLojDataInicio())){
            throw new BusinessRuleException("Data inicio antecede a data fim!");
        }
        Optional<Municipio> municipio = municipioRepository.findById(dbo.getMunId());
        Optional<TipoLoja> tipoLoja = tipoLojaRepository.findById(dbo.getTloId());
        if(municipio.isEmpty()) throw new BusinessRuleException("Municipio não encontrado"); else dbo.setMunicipio(municipio.get());
        if(tipoLoja.isEmpty()) throw new BusinessRuleException("Tipo loja não encontrado"); else dbo.setTipoLoja(tipoLoja.get());
        
        if (dbo.getLojStatus() == null) throw new BusinessRuleException("Status não pode ser nulo");
        
        
        return dbo;
    }

    /*
	@Transactional
	@PostMapping(value = "admin/v1/loja/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestLoja value) throws BusinessRuleException,BusinessSecurityException {
        Optional<Loja> loja = repository.findByLojCnpj(value.getLojCnpj());
        if(loja.isPresent()){
            throw new BusinessRuleException("CNPJ já utilizado por outra loja!");
        }
        Loja dbo = (Loja) mapper.copyToDbObject(value);
        validar(dbo);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}
	*/

	@Transactional
	@PutMapping(value = "admin/v1/loja/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestLoja value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Loja> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                if(repository.validarCnpj(value.getLojCnpj(), dbo.get().getId())) throw new BusinessRuleException("CNPJ já utilizado por outra loja!");

                //mapper.setMaxLevel(3);
                //mapper.copyToDbObject(value, dbo.get());
                
                dbo.get().setFrqId(Mapper.decryptId(value.getFrqId()));
                dbo.get().setMunId(Mapper.decryptId(value.getMunId()));
                dbo.get().setTloId(Mapper.decryptId(value.getTloId()));
                dbo.get().setLojCodigoInterno(value.getLojCodigoInterno());
                dbo.get().setLojStatus(value.getLojStatus());
                dbo.get().setLojQuantidadeSemanasAnalise(value.getLojQuantidadeSemanasAnalise());
                
                validar(dbo.get());

                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}


    @Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/loja/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Loja> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}


    @Transactional(propagation = Propagation.REQUIRED)
	@GetMapping(value = "admin/v1/loja/recupera/{id}")
	public ResponseEntity<RestLoja> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Loja> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo.isPresent()) {
        	//mapper.setMaxLevel(2);
            
            RestLoja rest = mapper.copyToRestObject(dbo.get(), RestLoja.class);
            //rest.setParametroList(mapper.copyToRestObject(parametroLojaRepository.findByLojId(dbo.get().getId()), RestParametroLoja.class));
            //rest.setFranqueado(mapper.copyToRestObject(dbo.get().getFranqueado(), RestFranqueado.class));
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/loja/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        
        String lojCnpj = allParams.getOrDefault("lojCnpj", null);
        String lojNome = allParams.getOrDefault("lojNome", null);
        String lojCodigoInterno = allParams.getOrDefault("lojCodigoInterno", null);
        
        if (lojNome != null) lojNome = "%" + lojNome + "%";

        Page<Loja> lojas = repository.listaLojas(pagination.queryWithPagination(pagina, Loja.class), lojCnpj, lojCodigoInterno, lojNome);
        List<RestLoja> restList =  mapper.copyToRestObject(lojas.getContent(), RestLoja.class);
        Pagination lojaPage = pagination.toResponse(pagina, lojas.getTotalPages(), restList);

        return new ResponseEntity<>(lojaPage, HttpStatus.OK);
	}

	@GetMapping(value = "admin/v1/loja/listaSimples")
    public ResponseEntity<List<RestLoja>> listaSimples() throws BusinessSecurityException {
        List<Loja> lojas = repository.listaSimples();
        List<RestLoja> restLojas = (List<RestLoja>) mapper.copyToRestObject(lojas, RestLoja.class);
        return new ResponseEntity<>(restLojas, HttpStatus.OK);
    }
}
