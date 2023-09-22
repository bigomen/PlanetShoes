package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Rede;
import com.luxfacta.planetshoes.api.repository.RedeRepository;
import com.luxfacta.planetshoes.api.rest.RestRede;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;

@RestController
public class RedeController extends AuthenticatedController {

    @Autowired
    private RedeRepository repository;


    @Transactional
    @GetMapping(value = "admin/v1/rede/lista")
    public ResponseEntity<Pagination> lista() throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        List<Rede> redeList = repository.findAll(pagination.queryWithoutPagination(Rede.class));
        List<RestRede> restList = mapper.copyToRestObject(redeList, RestRede.class);
        return new ResponseEntity<>(pagination.toResponse(repository.countRows(), restList), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "admin/v1/rede/listaSimples")
    public ResponseEntity<List<RestRede>> listaSimples() throws BusinessSecurityException {
        List<Rede> redes = repository.listaSimples();
        List<RestRede> restList = (List<RestRede>) mapper.copyToRestObject(redes, RestRede.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "admin/v1/rede/recupera/{id}")
    public ResponseEntity<RestRede> recupera(@PathVariable String id) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        Optional<Rede> rede = repository.findById(Mapper.decryptId(id));
        if(rede.isPresent()){
            RestRede rest = (RestRede) mapper.copyToRestObject(rede.get(), RestRede.class);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
        throw new BusinessSecurityException("Rede não encontrada");
    }

    @Transactional
    @PostMapping(value = "admin/v1/rede/novo")
    public ResponseEntity<Integer> novo(@RequestBody @Valid RestRede rest) throws BusinessSecurityException {
        if(!repository.existsByRedDescricao(rest.getRedDescricao())){
            Rede dbo = (Rede) mapper.copyToDbObject(rest);
            repository.save(dbo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new BusinessSecurityException("Rede já está cadastrada");
    }

    @Transactional
    @PutMapping(value = "admin/v1/rede/atualiza")
    public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestRede rest) throws BusinessSecurityException, NotFoundException {
        Optional<Rede> dbo = repository.findById(Mapper.decryptId(rest.getId()));
        if(dbo.isPresent()){
            if(repository.validarDescricao(rest.getRedDescricao(), dbo.get().getId())) throw new BusinessSecurityException("Descricao já utilizada por outra rede");
            mapper.copyToDbObject(rest, dbo.get());
            repository.save(dbo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException();
    }

    @Transactional
    @DeleteMapping(value = "admin/v1/rede/remove/{id}")
    public ResponseEntity<Integer> remove(@PathVariable String id) throws BusinessSecurityException, NotFoundException {
        if(repository.existsById(Mapper.decryptId(id))){
            repository.deleteById(Mapper.decryptId(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException();
    }
}
