package com.luxfacta.planetshoes.api.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.luxfacta.planetshoes.api.jwt.AccountCredentials;
import com.luxfacta.planetshoes.api.message.IBaseMessage;
import com.luxfacta.planetshoes.api.message.SuccessMessage;
import com.luxfacta.planetshoes.api.model.Loja;
import com.luxfacta.planetshoes.api.model.Telefone;
import com.luxfacta.planetshoes.api.model.Usuario;
import com.luxfacta.planetshoes.api.repository.GrupoRepository;
import com.luxfacta.planetshoes.api.repository.LojaRepository;
import com.luxfacta.planetshoes.api.repository.TelefoneRepository;
import com.luxfacta.planetshoes.api.repository.UsuarioRepository;
import com.luxfacta.planetshoes.api.rest.RestLoja;
import com.luxfacta.planetshoes.api.rest.RestTelefone;
import com.luxfacta.planetshoes.api.rest.RestUsuario;
import com.luxfacta.planetshoes.api.shared.EnviaEmail;
import com.luxfacta.planetshoes.api.shared.Pagination;

import jakarta.validation.Valid;


@RestController
public class UsuarioController extends AuthenticatedController {

    
	@Autowired
	private UsuarioRepository repository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;
    
	@Autowired
	private EnviaEmail sendEmail;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private GrupoRepository grupoRepository;

    
	private boolean validaComplexidade(String senha) {
		Pattern ptUpperCase = Pattern.compile(".*[A-Z].*");
		Pattern ptLowerCase = Pattern.compile(".*[a-z].*");
		Pattern ptNumbers = Pattern.compile(".*\\d.*");

		boolean hasUpperCase = ptUpperCase.matcher(senha).matches();
		boolean hasLowerCase = ptLowerCase.matcher(senha).matches();
		boolean hasNumbers = ptNumbers.matcher(senha).matches();
		boolean hasLength = senha.length() > 7;

		return !(!hasLowerCase || !hasUpperCase || !hasNumbers || !hasLength);
	}

	
	
	public boolean emailDisponivel(String email) {
        Optional<Usuario> usuValida = repository.findByUsuEmail(email);
        return usuValida.isEmpty();
	}

    private boolean validarTelefones(Collection<Telefone> telefones){
        Set<String> numeros = telefones.stream().map(Telefone::getTelNumero).collect(Collectors.toSet());
        return !Objects.equals(numeros.size(), telefones.size());
    }

	@Transactional
	@PostMapping(value = "admin/v1/usuario/novo")
	public ResponseEntity<Integer> novo(@RequestBody @Valid RestUsuario value) throws BusinessRuleException,BusinessSecurityException {
        if (!emailDisponivel(value.getUsuEmail().toLowerCase())) {
            throw new BusinessRuleException("Email em uso por outro usuário");
        }
        Usuario usu = (Usuario) mapper.copyToDbObject(value);
        usu.setUsuEmail(usu.getUsuEmail().toLowerCase());
        usu.setUsuDataCriacao(new Date());
        if(validarTelefones(usu.getTelefoneList())){
            throw new BusinessRuleException("Telefones Repetidos");
        }
        if(!grupoRepository.existsById(usu.getGruId())) throw new BusinessRuleException("Grupo não encontrado");

        for (RestLoja rl : value.getLojaList()) {
            Optional<Loja> optLoj = lojaRepository.findById(Mapper.decryptId(rl.getId()));
            if(optLoj.isEmpty()){
                throw new BusinessRuleException("Loja não encontrada!");
            }
            usu.getLojaList().add(optLoj.get());
        }

        repository.save(usu);

        _resetSenha(usu);

        return new ResponseEntity<>(HttpStatus.OK);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@PutMapping(value = "admin/v1/usuario/atualiza")
	public ResponseEntity<Integer> atualiza(@RequestBody @Valid RestUsuario value) throws BusinessRuleException, NotFoundException, BusinessSecurityException {
        Long usuId = Mapper.decryptId(value.getId());
        if (value.getId() != null) {
            Optional<Usuario> usu = repository.findById(usuId);
            if(repository.validarEmail(value.getUsuEmail(), Mapper.decryptId(value.getId()))){
                throw new BusinessRuleException("Email em uso por outro usuário");
            }
            if (usu.isPresent()) {

                mapper.copyToDbObject(value, usu.get());

                if(!grupoRepository.existsById(usu.get().getGruId())) throw new BusinessRuleException("Grupo não encontrado");

                for(RestLoja rl : value.getLojaList()){
                    Optional<Loja> loja = lojaRepository.findById(Mapper.decryptId(rl.getId()));
                    if(loja.isEmpty()){
                        throw new BusinessRuleException("Loja não encontrada");
                    }
                    usu.get().getLojaList().add(loja.get());
                }

                for(Loja l : new HashSet<>(usu.get().getLojaList())){
                    boolean encontrou = false;
                    for(RestLoja rl : value.getLojaList()){
                        if(Objects.equals(Mapper.decryptId(rl.getId()), l.getId())){
                            encontrou = true;
                        }
                    }
                    if(!encontrou){
                        usu.get().getLojaList().remove(l);
                    }
                }

                repository.save(usu.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();

	}
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@DeleteMapping(value = "admin/v1/usuario/remove/{id}")
	public ResponseEntity<Integer> remove(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        if (id != null) {
            Optional<Usuario> usu = repository.findById(Mapper.decryptId(id));
            if (usu.isPresent()) {
                repository.delete(usu.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
		throw new NotFoundException();
	}
	
	@Transactional
	@GetMapping(value = "admin/v1/usuario/recupera/{id}")
	public ResponseEntity<RestUsuario> recupera(@PathVariable String id) throws NotFoundException, BusinessSecurityException {
        Optional<Usuario> usu = repository.findById(Mapper.decryptId(id));
        if (usu.isPresent()) {
            mapper.setMaxLevel(2);
            RestUsuario rUsu = mapper.copyToRestObject(usu.get(), RestUsuario.class);
            rUsu.setLojaList(new HashSet<>(mapper.copyToRestObject(lojaRepository.lojasUsuario(usu.get().getId()), RestLoja.class)));
            rUsu.setTelefoneList(mapper.copyToRestObject(telefoneRepository.findByUsuario(usu.get()), RestTelefone.class));

            return new ResponseEntity<>(rUsu, HttpStatus.OK);
            
        }
		throw new NotFoundException();
	}


	@Transactional
	@GetMapping(value = "admin/v1/usuario/lista/{pagina}")
	public ResponseEntity<Pagination> lista(@PathVariable("pagina") int pagina, @RequestParam Map<String,String> allParams) throws BusinessSecurityException {
		
		String usuNome = allParams.getOrDefault("usuNome", null);
		String gruDescricao  = allParams.getOrDefault("grupo$gruDescricao", null);
		
		
		if (usuNome != null) usuNome = "%" + usuNome + "%";
		if (gruDescricao != null) gruDescricao = "%" + gruDescricao + "%";
				
        Page<Usuario> itUsu = repository.listaUsuarios(pagination.queryWithPagination(pagina, Usuario.class), usuNome, gruDescricao);
        mapper.setMaxLevel(2);
        List<RestUsuario> itRestUsu =  (List<RestUsuario>) mapper.copyToRestObject(itUsu.getContent(), RestUsuario.class);

        return new ResponseEntity<>(pagination.toResponse(pagina, itUsu.getTotalPages(), itRestUsu), HttpStatus.OK);
	}


	
	@Transactional
	@PostMapping(value = "/updatePassword")
	public ResponseEntity<IBaseMessage> alterarSenha(@RequestBody AccountCredentials value)  throws BusinessRuleException {
        Optional<Usuario> optUsu = repository.findById(super.getIdUsuario());
        if (optUsu.isPresent() && value.getPassword() != null
                && passwordEncoder.matches(value.getPassword(), optUsu.get().getUsuSenha())) {

            Usuario usu = optUsu.get();


            if (!validaComplexidade(value.getNewPassword())) {
                throw new BusinessRuleException("Senha deve ter letras maiúsculas, minúsculas e números");
            }

            usu.setUsuToken(null);
            usu.setUsuTokenExpiracao(null);


            String senhaCrypto = passwordEncoder.encode(value.getNewPassword());
            usu.setUsuSenha(senhaCrypto);
            repository.save(usu);

            return new ResponseEntity<>(new SuccessMessage("Senha atualizada com sucesso"), HttpStatus.OK);
        }
        throw new BusinessRuleException("Senha inválida ou não informada");
	}

	private void _resetSenha(Usuario usu) {
			
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30);
        usu.setUsuToken(UUID.randomUUID().toString());
        usu.setUsuTokenExpiracao(cal.getTime());
        repository.save(usu);

		try {
			EnviaEmail.Mensagem mensagem = sendEmail.novaMensagem();

			mensagem.setAssunto(config.getProperty("email.resetsenha.assunto"));

			String corpo = config.getProperty("email.resetsenha.corpo");
            if (corpo != null) {
                corpo = corpo.replace("#usuario#", usu.getUsuNome());
                corpo = corpo.replace("#token#", usu.getUsuToken());
            }
			mensagem.setMensagem(corpo);

			mensagem.setEmailOrigem(config.getProperty("email.resetsenha.emailOrigem"));
			mensagem.setNomeOrigem(config.getProperty("email.resetsenha.nomeOrigem"));

			mensagem.setNomeDestinatario(usu.getUsuNome());
			mensagem.setEmailDestinatario(usu.getUsuEmail());

			sendEmail.enviar(mensagem);
		} catch (Exception e) {
		}

	}


	/*
	@Transactional
    @GetMapping(value = "admin/v1/usuario/listaSimples")
    public ResponseEntity<Iterable<RestUsuario>> listaSimples() throws BusinessSecurityException {
        Iterable<Usuario> usuarios = repository.findAll();
        mapper.setMaxLevel(1);
        List<RestUsuario> restUsuarios = (List<RestUsuario>) mapper.copyToRestObject(usuarios, RestUsuario.class);
        return new ResponseEntity<>(restUsuarios, HttpStatus.OK);
    }
    */
}