package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.exception.NotFoundException;
import com.luxfacta.planetshoes.api.model.Movimentacao;
import com.luxfacta.planetshoes.api.repository.MovimentacaoRepository;
import com.luxfacta.planetshoes.api.rest.RestMovimentacao;
import com.luxfacta.planetshoes.api.shared.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
public class MovimentacaoController extends AuthenticatedController {

	@Autowired
	private MovimentacaoRepository repository;
    
    /*
	@Transactional
	@PostMapping(value = "admin/v1/movimentacao/novo")
	public ResponseEntity<Integer> novo(@RequestBody RestMovimentacao value) throws BusinessRuleException,BusinessSecurityException {
        Movimentacao dbo = (Movimentacao) mapper.copyToDbObject(value);
        repository.save(dbo);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "admin/v1/movimentacao/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody RestMovimentacao value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        if (value.getId() != null) {
            Long id = Mapper.decryptId(value.getId());
            Optional<Movimentacao> dbo = repository.findById(id);
            if (dbo.isPresent()) {
                
                mapper.copyToDbObject(value, dbo.get());
                repository.save(dbo.get());

                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@DeleteMapping(value = "admin/v1/movimentacao/remove/{id}")
	public ResponseEntity<Integer> remove(String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Movimentacao> dbo = repository.findById(Mapper.decryptId(id));
            if (dbo.isPresent()) {
                
                repository.delete(dbo.get());
                
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	*/
    
	@GetMapping(value = "admin/v1/movimentacao/recupera/{id}")
	public ResponseEntity<RestMovimentacao> recupera(String id) throws NotFoundException, BusinessSecurityException {
        Optional<Movimentacao> dbo = repository.findById(Mapper.decryptId(id));
        if (dbo != null && dbo.isPresent()) {
            
            RestMovimentacao rest = (RestMovimentacao) mapper.copyToRestObject(dbo.get(), RestMovimentacao.class);
            
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }
		throw new NotFoundException();
	}

	@GetMapping(value = "admin/v1/movimentacao/lista/{pagina}/{loja}/{semana}")
	public ResponseEntity<Pagination> lista(@PathVariable int pagina, @PathVariable String loja, @PathVariable String semana) throws BusinessSecurityException {
        mapper.setMaxLevel(1);
        
        Iterable<Movimentacao> itDbo = repository.findByLojIdAndSemId(pagination.queryWithPagination(pagina, Movimentacao.class), Mapper.decryptId(loja), Mapper.decryptId(semana));
        List<RestMovimentacao> restList =  (List<RestMovimentacao>) mapper.copyToRestObject(itDbo, RestMovimentacao.class);
        
        return new ResponseEntity<>(pagination.toResponse(pagina, repository.countRows(), restList), HttpStatus.OK);
	}

    @Transactional
    @PostMapping(value = "admin/v1/movimentacao/inserirArquivo")
    public ResponseEntity<Integer> inserirArquivo(@RequestBody HashMap<String, String> hash) throws IOException {
        String file = hash.get("file");
        byte[] decodedFile = Base64.getDecoder().decode(file.getBytes(StandardCharsets.UTF_8));
        try (OutputStream stram = new FileOutputStream("/extra/rcerqueira/movimentacao_produtos_loja.csv")){
            stram.write(decodedFile);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping(value = "admin/v1/movimentacao/inserirArquivoBase64")
    public ResponseEntity<HashMap<String, String>> encodeBase64(@RequestBody MultipartFile file) throws IOException {
        byte[] arquivo = Base64.getEncoder().encode(file.getBytes());
        String base64 = new String(arquivo);
        HashMap<String, String> hash = new HashMap<>();
        hash.put("file", base64);
        return new ResponseEntity<>(hash, HttpStatus.OK);
    }
	
}
