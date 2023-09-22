package com.luxfacta.planetshoes.api.controller;

import com.luxfacta.planetshoes.api.exception.BusinessRuleException;
import com.luxfacta.planetshoes.api.jwt.AccountCredentials;
import com.luxfacta.planetshoes.api.jwt.TokenAuthenticationService;
import com.luxfacta.planetshoes.api.message.ErrorMessage;
import com.luxfacta.planetshoes.api.message.IBaseMessage;
import com.luxfacta.planetshoes.api.message.SuccessMessage;
import com.luxfacta.planetshoes.api.model.Loja;
import com.luxfacta.planetshoes.api.model.Permissao;
import com.luxfacta.planetshoes.api.model.Usuario;
import com.luxfacta.planetshoes.api.repository.UsuarioRepository;
import com.luxfacta.planetshoes.api.shared.EnviaEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;


@RestController
public class AutenticacaoController {

    @Autowired
    private Environment env;
    
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private EnviaEmail sendEmail;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
    
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

	// REFRESH TOKEN
	@PostMapping(value = "/refresh")
	public ResponseEntity<Object> refresh() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "auth/v1/usuario/checaEmail/{email}")
	public ResponseEntity<IBaseMessage> checaEmail(@PathVariable String email) {
		
        if (emailDisponivel(email)) {
            return new ResponseEntity<>(new SuccessMessage("OK"), HttpStatus.OK);
        }
		return new ResponseEntity<>(new ErrorMessage("Email já em uso por outro usuário","ERROR"), HttpStatus.OK);
		
	}
	
	private boolean emailDisponivel(String email) {
        Optional<Usuario> usuValida = repository.findByUsuEmail(email);
        return usuValida.isPresent();
	}


	@Transactional
	@PostMapping(value = "/forgot")
	public ResponseEntity<IBaseMessage> forgot(@RequestBody AccountCredentials value) {
		Optional<Usuario> usuIt = repository.findByUsuEmail(value.getUsername());
        if (usuIt.isPresent()) {
            Usuario usu = usuIt.get();
			if (usu.getUsuDataExclusao() == null) {
				_resetSenha(usu);
			}
		}
		return new ResponseEntity<>(new SuccessMessage("Email de reset de senha enviado com sucesso"), HttpStatus.OK);
	}


	@Transactional
	@PostMapping(value = "/reset/{uuid}")
	public ResponseEntity<IBaseMessage> resetSenha(@PathVariable String uuid, @RequestBody AccountCredentials value) throws BusinessRuleException {
        Optional<Usuario> optUsu = repository.findByUsuToken(uuid);
        if (optUsu.isPresent() && value.getPassword() != null) {

            Usuario usu = optUsu.get();

            if (usu.getUsuTokenExpiracao().getTime() < Calendar.getInstance().getTimeInMillis()) {
                usu.setUsuToken(null);
                usu.setUsuTokenExpiracao(null);
                repository.save(usu);
                throw new BusinessRuleException("Token expirado, solicite novamente a senha");
            }

            if (!validaComplexidade(value.getPassword())) {
                throw new BusinessRuleException("Senha deve ter letras maiúsculas, minúsculas e números");
            }

            usu.setUsuToken(null);
            usu.setUsuTokenExpiracao(null);
            String senhaCrypto = passwordEncoder.encode(value.getPassword());
            usu.setUsuSenha(senhaCrypto);
            repository.save(usu);

            try {
                EnviaEmail.Mensagem mensagem = sendEmail.novaMensagem();
                mensagem.setAssunto(env.getProperty("email.senhaAtualizada.assunto"));
                String corpo = env.getProperty("email.senhaAtualizada.corpo");
                if (corpo != null)
                    corpo = corpo.replace("#usuario#", usu.getUsuNome());

                mensagem.setMensagem(corpo);
                mensagem.setEmailOrigem(env.getProperty("email.senhaAtualizada.emailOrigem"));
                mensagem.setNomeOrigem(env.getProperty("email.senhaAtualizada.nomeOrigem"));
                mensagem.setNomeDestinatario(usu.getUsuNome());
                mensagem.setEmailDestinatario(usu.getUsuEmail());
                sendEmail.enviar(mensagem);
            } catch (Exception e) {
            }

            return new ResponseEntity<>(new SuccessMessage("Senha atualizada com sucesso"), HttpStatus.OK);
        }
        throw new BusinessRuleException("Token inválido ou senha não informada");
	}

    @Transactional
	@PostMapping(value = "/login")
	public ResponseEntity<HashMap<String, String>> loginSenha(@RequestBody AccountCredentials value)  throws BusinessRuleException {
        Optional<Usuario> usuOpt = repository.findByUsuEmail(value.getUsername());

        if (usuOpt.isPresent()) {
            Usuario usu = usuOpt.get();
            if (usu.getUsuSenha() != null && usu.getUsuDataExclusao() == null 
            		//&& passwordEncoder.matches(value.getPassword(), usu.getUsuSenha())
            		) {
                
                List<String> rolesGrupo = new ArrayList<>();
                List<String> lojas = new ArrayList<>();
                for (Permissao per : usu.getGrupo().getPermissaoList()) {
                    rolesGrupo.add(per.getId().toString());
                }

                for (Loja l : usu.getLojaList()) {
                    lojas.add(l.getId().toString());
                }
                
                String tokenAuth = TokenAuthenticationService.generateToken(usu.getUsuEmail(), rolesGrupo, lojas, usu.getUsuNome(), usu.getId(), Long.parseLong(env.getProperty("security.expiration")), env.getProperty("security.secret"));
                String tokenRefresh = TokenAuthenticationService.generateToken(usu.getUsuEmail(), rolesGrupo, lojas, usu.getUsuNome(), usu.getId(), Long.parseLong(env.getProperty("security.refresh.expiration")), env.getProperty("security.secret"));
                
                HttpHeaders header = new HttpHeaders();
                header.add(TokenAuthenticationService.HEADER_AUTHORIZATION, tokenAuth);
                header.add(TokenAuthenticationService.HEADER_REFRESH, tokenRefresh);

                HashMap<String, String> loginStatus = new HashMap<>();
                loginStatus.put("message", "Login com sucesso");
                loginStatus.put("username", usu.getUsuNome());
                return new ResponseEntity<>(loginStatus, header, HttpStatus.OK);
        	}
            
        }
        throw new BusinessRuleException("Usuário/senha inválida");
    }
	
	private void _resetSenha(Usuario usu) {
			
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 30);
        usu.setUsuToken(UUID.randomUUID().toString());
        usu.setUsuTokenExpiracao(cal.getTime());
        repository.save(usu);

		try {
			EnviaEmail.Mensagem mensagem = sendEmail.novaMensagem();

			mensagem.setAssunto(env.getProperty("email.resetsenha.assunto"));

			String corpo = env.getProperty("email.resetsenha.corpo");
            if (corpo != null) {
                corpo = corpo.replace("#usuario#", usu.getUsuNome());
                corpo = corpo.replace("#token#", usu.getUsuToken());
            }
			mensagem.setMensagem(corpo);

			mensagem.setEmailOrigem(env.getProperty("email.resetsenha.emailOrigem"));
			mensagem.setNomeOrigem(env.getProperty("email.resetsenha.nomeOrigem"));

			mensagem.setNomeDestinatario(usu.getUsuNome());
			mensagem.setEmailDestinatario(usu.getUsuEmail());

			sendEmail.enviar(mensagem);
		} catch (Exception e) {
		}

	}

	

	
}