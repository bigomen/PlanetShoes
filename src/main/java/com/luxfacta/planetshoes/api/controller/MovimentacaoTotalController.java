package com.luxfacta.planetshoes.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.luxfacta.planetshoes.api.base.AuthenticatedController;
import com.luxfacta.planetshoes.api.exception.BusinessSecurityException;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalArtigo;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalArtigoPorLoja;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalCorPorArtigo;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalCorPorLojaArtigo;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalFranqueado;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalFranqueadoPorArtigoCor;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalLojaPorFranqueado;
import com.luxfacta.planetshoes.api.model.views.MovimentacaoTotalLojaPorFranqueadoArtigoCor;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalArtigoPorLojaRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalArtigoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalCorPorArtigoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalCorPorLojaArtigoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalFranqueadoPorArtigoCorRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalFranqueadoRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalLojaPorFranqueadoArtigoCorRepository;
import com.luxfacta.planetshoes.api.repository.MovimentacaoTotalLojaPorFranqueadoRepository;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalArtigo;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalArtigoPorLoja;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalCorPorArtigo;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalCorPorLojaArtigo;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalFranqueado;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalFranqueadoPorArtigoCor;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalLojaPorFranqueado;
import com.luxfacta.planetshoes.api.rest.RestMovimentacaoTotalLojaPorFranqueadoArtigoCor;

@RestController
public class MovimentacaoTotalController extends AuthenticatedController {
    @Autowired
    private MovimentacaoTotalFranqueadoRepository totalFranqueadoRepository;

    @Autowired
    private MovimentacaoTotalFranqueadoPorArtigoCorRepository totalFranqueadoPorArtigoCorRepository;

    @Autowired
    private MovimentacaoTotalArtigoPorLojaRepository totalArtigoPorLojaRepository;

    @Autowired
    private MovimentacaoTotalLojaPorFranqueadoRepository totalLojaPorFranqueadoRepository;

    @Autowired
    private MovimentacaoTotalCorPorLojaArtigoRepository totalCorPorLojaArtigoRepository;

    @Autowired
    private MovimentacaoTotalArtigoRepository totalArtigoRepository;

    @Autowired
    private MovimentacaoTotalCorPorArtigoRepository totalCorPorArtigoCorRepository;

    @Autowired
    private MovimentacaoTotalLojaPorFranqueadoArtigoCorRepository totalLojaPorFranqueadoArtigoCorRepository;

    
    /*




    

    


*/
    @Transactional
    @GetMapping(value = "admin/v1/movimentacaoTotal/franqueado/{semana}")
    public ResponseEntity<List<RestMovimentacaoTotalFranqueado>> totalFranqueado(@PathVariable String semana) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalFranqueado> totalRedes = totalFranqueadoRepository.findAllBySemId(Mapper.decryptId(semana));
        List<RestMovimentacaoTotalFranqueado> restList = mapper.copyToRestObject(totalRedes, RestMovimentacaoTotalFranqueado.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    
    @Transactional
    @GetMapping(value = "admin/v1/movimentacaoTotal/franqueado/{semana}/{artigo}/{cor}")
    public ResponseEntity<List<RestMovimentacaoTotalFranqueadoPorArtigoCor>> totalFranqueadoPorArtigoCor(@PathVariable String semana,@PathVariable String artigo,@PathVariable String cor) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalFranqueadoPorArtigoCor> totalRedes = totalFranqueadoPorArtigoCorRepository.findAllBySemIdAndArtIdAndCorId(Mapper.decryptId(semana),Mapper.decryptId(artigo),Mapper.decryptId(cor));
        List<RestMovimentacaoTotalFranqueadoPorArtigoCor> restList = mapper.copyToRestObject(totalRedes, RestMovimentacaoTotalFranqueadoPorArtigoCor.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    
    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/artigo/{semana}/{loja}"})
    public ResponseEntity<List<RestMovimentacaoTotalArtigoPorLoja>> totalArtigoPorLoja(@PathVariable String semana, @PathVariable String loja) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalArtigoPorLoja> totalLojas = totalArtigoPorLojaRepository.findAllBySemIdAndLojId(Mapper.decryptId(semana), Mapper.decryptId(loja));
        List<RestMovimentacaoTotalArtigoPorLoja> restList = mapper.copyToRestObject(totalLojas, RestMovimentacaoTotalArtigoPorLoja.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }
    

    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/loja/{semana}/{franqueado}"})
    public ResponseEntity<List<RestMovimentacaoTotalLojaPorFranqueado>> totalLojaPorFranqueado(@PathVariable String semana, @PathVariable String franqueado) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalLojaPorFranqueado> totalLojas = totalLojaPorFranqueadoRepository.findAllBySemIdAndFrqId(Mapper.decryptId(semana), Mapper.decryptId(franqueado));
        List<RestMovimentacaoTotalLojaPorFranqueado> restList = mapper.copyToRestObject(totalLojas, RestMovimentacaoTotalLojaPorFranqueado.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/loja/{semana}/{franqueado}/{artigo}/{cor}"})
    public ResponseEntity<List<RestMovimentacaoTotalLojaPorFranqueadoArtigoCor>> totalLojaPorFranqueadoArtigoCor(@PathVariable String semana, @PathVariable String franqueado, @PathVariable String artigo, @PathVariable String cor) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalLojaPorFranqueadoArtigoCor> totalLojas = totalLojaPorFranqueadoArtigoCorRepository.findAllBySemIdAndArtIdAndCorIdAndFrqId(Mapper.decryptId(semana), Mapper.decryptId(artigo), Mapper.decryptId(cor),  Mapper.decryptId(franqueado));
        List<RestMovimentacaoTotalLojaPorFranqueadoArtigoCor> restList = mapper.copyToRestObject(totalLojas, RestMovimentacaoTotalLojaPorFranqueadoArtigoCor.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }
    
    
    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/artigoCor/{semana}/{loja}/{artigo}"})
    public ResponseEntity<List<RestMovimentacaoTotalCorPorLojaArtigo>> totalArtigoCorPorLoja(@PathVariable String semana, @PathVariable String loja, @PathVariable String artigo) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalCorPorLojaArtigo> totalLojas = totalCorPorLojaArtigoRepository.findAllBySemIdAndLojIdAndArtId(Mapper.decryptId(semana),Mapper.decryptId(loja),Mapper.decryptId(artigo) );
        List<RestMovimentacaoTotalCorPorLojaArtigo> restList = mapper.copyToRestObject(totalLojas, RestMovimentacaoTotalCorPorLojaArtigo.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }
    
    @Transactional
    @GetMapping(value = "admin/v1/movimentacaoTotal/artigo/{semana}")
    public ResponseEntity<List<RestMovimentacaoTotalArtigo>> totalArtigo(@PathVariable String semana) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalArtigo> totalArtigos = totalArtigoRepository.findAllBySemId(Mapper.decryptId(semana));
        List<RestMovimentacaoTotalArtigo> restList = mapper.copyToRestObject(totalArtigos, RestMovimentacaoTotalArtigo.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/artigoCor/{semana}/{artigo}"})
    public ResponseEntity<List<RestMovimentacaoTotalCorPorArtigo>> totalCorPorArtigo(@PathVariable String semana, @PathVariable String artigo) throws BusinessSecurityException {
    	// OKC
        List<MovimentacaoTotalCorPorArtigo> totalArtigoCor = totalCorPorArtigoCorRepository.findAllBySemIdAndArtId(Mapper.decryptId(semana), Mapper.decryptId(artigo));
        List<RestMovimentacaoTotalCorPorArtigo> restList = mapper.copyToRestObject(totalArtigoCor, RestMovimentacaoTotalCorPorArtigo.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    
    
    /*
    
    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/loja/{semana}/{artigo}/{cor}"})
    public ResponseEntity<List<RestMovimentacaoTotalCorPorArtigo>> totalPorArtigoCor(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor) throws BusinessSecurityException {
        List<MovimentacaoTotalCorPorArtigo> totalArtigoCor = totalCorPorArtigoCorRepository.findAllBySemIdAndArtIdAndCorId(Mapper.decryptId(semana), Mapper.decryptId(artigo), Mapper.decryptId(cor));
        List<RestMovimentacaoTotalCorPorArtigo> restList = mapper.copyToRestObject(totalArtigoCor, RestMovimentacaoTotalCorPorArtigo.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/artigoCorLoja/{semana}/{artigo}/{cor}/{loja}"})
    public ResponseEntity<List<RestMovimentacaoTotalPorArtigoCorLoja>> totalPorArtigoCorLoja(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor, @PathVariable String loja) throws BusinessSecurityException {
        List<MovimentacaoTotalPorArtigoCorLoja> totalArtigoCorLoja = artigoCorLojaRepository.findAllBySemIdAndArtIdAndCorIdAndLojId(
                    Mapper.decryptId(semana), Mapper.decryptId(artigo), Mapper.decryptId(cor), Mapper.decryptId(loja));
        List<RestMovimentacaoTotalPorArtigoCorLoja> restList = mapper.copyToRestObject(totalArtigoCorLoja, RestMovimentacaoTotalPorArtigoCorLoja.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = {"admin/v1/movimentacaoTotal/artigoCorLoja/{semana}/{artigo}/{cor}"})
    public ResponseEntity<List<RestMovimentacaoTotalPorArtigoCorLoja>> totalPorArtigoCorLoja(@PathVariable String semana, @PathVariable String artigo, @PathVariable String cor) throws BusinessSecurityException {
        List<MovimentacaoTotalPorArtigoCorLoja> totalArtigoCorLoja = artigoCorLojaRepository.findAllBySemIdAndArtIdAndCorId(Mapper.decryptId(semana), Mapper.decryptId(artigo), Mapper.decryptId(cor));
        List<RestMovimentacaoTotalPorArtigoCorLoja> restList = mapper.copyToRestObject(totalArtigoCorLoja, RestMovimentacaoTotalPorArtigoCorLoja.class);
        return new ResponseEntity<>(restList, HttpStatus.OK);
    }


    @Transactional
    @GetMapping(value = "admin/v1/testes/criptografar/{id}")
    public ResponseEntity<String> testeCriptografar(@PathVariable Long id) throws BusinessSecurityException {
        return new ResponseEntity<>(Mapper.encryptId(id), HttpStatus.OK);
    }
    */
}
