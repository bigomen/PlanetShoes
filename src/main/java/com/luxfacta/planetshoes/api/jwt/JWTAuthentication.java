
package com.luxfacta.planetshoes.api.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 * @author rcerqueira
 */
public class JWTAuthentication extends UsernamePasswordAuthenticationToken {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long[] idLoja;
    private final Long idUsuario;

    public JWTAuthentication(Object principal, Collection<? extends GrantedAuthority> authorities, Long[] idLoja, Long idUsuario) {
        super(principal, null, authorities);
        this.idLoja = idLoja;
        this.idUsuario = idUsuario;
    }

   
    public Long[] getIdLoja() {
        return this.idLoja;
    }
    public Long getIdUsuario() {
        return this.idUsuario;
    }
    
}
