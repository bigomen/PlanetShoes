package com.luxfacta.planetshoes.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Franqueado;
import com.luxfacta.planetshoes.api.repository.FranqueadoRepository;
import com.luxfacta.planetshoes.api.rest.RestFranqueado;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;

@RestController
public class FranqueadoController extends AuthenticatedController {

    @Autowired
    private FranqueadoRepository repository;


    @Transactional
    @GetMapping(value = "admin/v1/franqueado/lista/{pagina}")
    public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
        mapper.setMaxLevel(2);
        String frqCodigo = allParams.getOrDefault("frqCodigo", null);
        String frqNome = allParams.getOrDefault("frqNome", null);

        if (frqNome != null) frqNome = "%" + frqNome + "%";
        
        Page<Franqueado> frqList = repository.listaFranqueados(pagination.queryWithPagination(pagina, Franqueado.class), frqCodigo, frqNome);
        List<RestFranqueado> restList = mapper.copyToRestObject(frqList.getContent(), RestFranqueado.class);
        return new ResponseEntity<>(pagination.toResponse(pagina, frqList.getTotalPages(), restList), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "admin/v1/franqueado/listaSimples")
    public ResponseEntity<List<RestFranqueado>> listaSimples() throws BusinessSecurityException {
        List<Franqueado> redes = repository.listaSimples();
        List<RestFranqueado> restList = (List<RestFranqueado>) mapper.copyToRestObject(redes, RestFranqueado.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "admin/v1/franqueado/recupera/{id}")
    public ResponseEntity<RestFranqueado> recupera(@PathVariable String id) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        Optional<Franqueado> rede = repository.findById(Mapper.decryptId(id));
        if(rede.isPresent()){
            RestFranqueado rest = mapper.copyToRestObject(rede.get(), RestFranqueado.class);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
        throw new BusinessSecurityException("Franqueado não encontrada");
    }

    @Transactional
    @PostMapping(value = "admin/v1/franqueado/novo")
    public ResponseEntity<Integer> novo(@RequestBody @Valid RestFranqueado rest) throws BusinessSecurityException {
        if(Boolean.FALSE.equals(repository.existsByFrqCodigoAndRedId(rest.getFrqCodigo(), Mapper.decryptId(rest.getRedId())))) {
            Franqueado dbo = (Franqueado) mapper.copyToDbObject(rest);
            repository.save(dbo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new BusinessSecurityException("Franqueado já está cadastrado");
    }

    @Transactional
    @PutMapping(value = "admin/v1/franqueado/atualiza")
    public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestFranqueado rest) throws BusinessSecurityException, NotFoundException {
        Optional<Franqueado> dbo = repository.findById(Mapper.decryptId(rest.getId()));
        if(dbo.isPresent()){
            if(Boolean.TRUE.equals(repository.validarCodigo(rest.getFrqCodigo(), dbo.get().getRedId(), dbo.get().getId()))) throw new BusinessSecurityException("Código já utilizado por outro franqueado.");
            mapper.copyToDbObject(rest, dbo.get());
            repository.save(dbo.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException();
    }

    @Transactional
    @DeleteMapping(value = "admin/v1/franqueado/remove/{id}")
    public ResponseEntity<Integer> remove(@PathVariable String id) throws BusinessSecurityException, NotFoundException {
        if(repository.existsById(Mapper.decryptId(id))){
            repository.deleteById(Mapper.decryptId(id));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new NotFoundException();
    }
}
