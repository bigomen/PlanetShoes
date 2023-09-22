package com.luxfacta.planetshoes.api.controller;

import java.util.HashSet;
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
import com.luxfacta.planetshoes.api.model.Grupo;
import com.luxfacta.planetshoes.api.model.Permissao;
import com.luxfacta.planetshoes.api.repository.GrupoRepository;
import com.luxfacta.planetshoes.api.repository.PermissaoRepository;
import com.luxfacta.planetshoes.api.rest.RestGrupo;
import com.luxfacta.planetshoes.api.rest.RestPermissao;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class GrupoController extends AuthenticatedController {

	@Autowired
	private GrupoRepository repository;

    @Autowired
    private PermissaoRepository permissaoRepository;

	@Transactional
	@PostMapping(value = "admin/v1/grupo/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestGrupo value) throws BusinessRuleException,BusinessSecurityException {
        Grupo dbo = (Grupo) mapper.copyToDbObject(value);

        for(RestPermissao rp : value.getPermissaoList()){
            Optional<Permissao> permissao = permissaoRepository.findById(Mapper.decryptId(rp.getId()));
            if(permissao.isEmpty()) throw new BusinessRuleException("Permissão não existe"); else dbo.getPermissaoList().add(permissao.get());
        }

        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/grupo/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestGrupo value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Grupo> dbo = repository.findById(id);
            if (dbo.isPresent()) {

                mapper.copyToDbObject(value, dbo.get());

                dbo.get().getPermissaoList().clear();
                for(RestPermissao rp : value.getPermissaoList()){
                    Optional<Permissao> permissao = permissaoRepository.findById(Mapper.decryptId(rp.getId()));
                    if(permissao.isEmpty()) throw new BusinessRuleException("Permissão não existe"); else dbo.get().getPermissaoList().add(permissao.get());
                }

                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/grupo/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Grupo> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@GetMapping(value = "admin/v1/grupo/recupera/{id}")
	public ResponseEntity<RestGrupo> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Grupo> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestGrupo rest = (RestGrupo) mapper.copyToRestObject(dbo.get(), RestGrupo.class);
            rest.setPermissaoList(new HashSet<>(mapper.copyToRestObject(permissaoRepository.getPermissoes(dbo.get()), RestPermissao.class)));
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/grupo/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        String gruDescricao  = allParams.getOrDefault("gruDescricao", null);
		if (gruDescricao != null) gruDescricao = "%" + gruDescricao + "%";
		
        Page<Grupo> itDbo = repository.listaGrupos(pagination.queryWithPagination(pagina, Grupo.class), gruDescricao );
        List<RestGrupo> restList =  (List<RestGrupo>) mapper.copyToRestObject(itDbo.getContent(), RestGrupo.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, itDbo.getTotalPages(), restList), HttpStatus.OK);
	}

    @GetMapping(value = "admin/v1/grupo/listaSimples")
    public ResponseEntity<List<RestGrupo>> listaSimples() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        List<Grupo> grupos = repository.listaSimples();
        List<RestGrupo> restGrupos = (List<RestGrupo>) mapper.copyToRestObject(grupos, RestGrupo.class);
        return new ResponseEntity<>(restGrupos, HttpStatus.OK);
    }
}
