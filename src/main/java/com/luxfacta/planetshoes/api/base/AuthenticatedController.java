package com.luxfacta.planetshoes.api.base;

import com.luxfacta.planetshoes.api.jwt.JWTAuthentication;
import com.luxfacta.planetshoes.api.shared.Pagination;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@SecurityRequirement(name = "bearerAuth")
public abstract class AuthenticatedController  {
   
    @Autowired
    protected Environment config;

	protected final Mapper mapper = new Mapper();
    protected final Pagination pagination = new Pagination();

    protected boolean hasPermission(Long permission) {
        JWTAuthentication jwtAuth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
    	for (GrantedAuthority ga : jwtAuth.getAuthorities()) {
    		if (permission.toString().equals(ga.getAuthority()))
    			return true;
    	}
    	return false;
    }
    
    protected Long[] getIdLoja() {
        JWTAuthentication jwtAuth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return jwtAuth.getIdLoja();
    }

    protected Long getIdUsuario() {
        JWTAuthentication jwtAuth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return jwtAuth.getIdUsuario();
    }

    protected String getLoginUsuario() {
        JWTAuthentication jwtAuth = (JWTAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return jwtAuth.getPrincipal().toString();
    }
    
    
}