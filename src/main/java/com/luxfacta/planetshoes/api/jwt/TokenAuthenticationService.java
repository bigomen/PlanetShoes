
package com.luxfacta.planetshoes.api.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TokenAuthenticationService {
    
    private final long EXPIRATION_TIME;
    private final long REFRESH_EXPIRATION_TIME;
    private final String SECRET;
    public static final String HEADER_AUTHORIZATION = "Authorization";
	public static final String HEADER_REFRESH = "Refresh";
	public static final String BEARER = "Bearer ";
    public static final String USERNAME = "Username";
    
    public TokenAuthenticationService(Environment env) {
        this.EXPIRATION_TIME = Long.parseLong(env.getProperty("security.expiration"));
        this.REFRESH_EXPIRATION_TIME = Long.parseLong(env.getProperty("security.refresh.expiration"));
        this.SECRET = env.getProperty("security.secret"); 
    }

    /*
    public void addAuthentication(HttpServletResponse response, String username, List<String> rolesGrupo, 
    		List<String> loja, String nomeUsuario, Long usuario) {
        
		response.addHeader(HEADER_STRING, generateToken(username, rolesGrupo, loja, nomeUsuario, usuario, EXPIRATION_TIME, SECRET));
		response.addHeader(HEADER_REFRESH, generateToken(username, rolesGrupo, loja, nomeUsuario, usuario, REFRESH_EXPIRATION_TIME, SECRET));
    }
    */
    
    public Authentication getRefreshAuthentication(HttpServletRequest request,	HttpServletResponse response)
	{
		String token = request.getHeader(HEADER_AUTHORIZATION);
        try {
			if (token != null) {

                SignedJWT jwsObject = SignedJWT.parse( token.replace(BEARER, "") );
                JWSVerifier verifier = new MACVerifier( SECRET.getBytes() );
                if ( jwsObject.verify( verifier )) {
                    Date expirationTime = jwsObject.getJWTClaimsSet().getExpirationTime();
                    if (expirationTime.before(new Date())) {
                        return null;
                    }



                    JWTClaimsSet claim =  JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
    			
    				String email = claim.getSubject();
                    List<String> role = claim.getStringListClaim("role");
                    List<String> loja = claim.getStringListClaim("loja");
                    String nomeUsuario = claim.getStringClaim("name");
                    Long usuario = claim.getLongClaim("usuario");

                    Long[] lojaLongList = new Long[loja.size()];
                    for (int i = 0; i < loja.size(); i++) {
                        lojaLongList[i] = Long.valueOf(loja.get(i));
                    }
                    

                    response.addHeader(HEADER_AUTHORIZATION, generateToken(email, role, loja, nomeUsuario, usuario, EXPIRATION_TIME, SECRET));
                    response.addHeader(HEADER_REFRESH, generateToken(email, role, loja, nomeUsuario, usuario, REFRESH_EXPIRATION_TIME, SECRET));

                    return new JWTAuthentication(email, getGrantedAuthorities(role), lojaLongList, usuario);
                }
            }
        } catch (Exception e) {
            return null;
        }
		return null;
	}
    
    
    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION);

        try {
            if (token != null) {
                
                SignedJWT jwsObject = SignedJWT.parse( token.replace(BEARER, "") );
                JWSVerifier verifier = new MACVerifier( SECRET.getBytes() );
                if ( jwsObject.verify( verifier )) {
                    
                    Date expirationTime = jwsObject.getJWTClaimsSet().getExpirationTime();
                    if (expirationTime.before(new Date())) {
                        return null;
                    }
                    
                    JWTClaimsSet claim =  JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
    			
    				String email = claim.getSubject();
                    List<String> role = claim.getStringListClaim("role");
                    List<String> loja = claim.getStringListClaim("loja");
                    Long usuario = claim.getLongClaim("usuario");

                    Long[] lojaLongList = new Long[loja.size()];
                    for (int i = 0; i < loja.size(); i++) {
                        lojaLongList[i] = Long.valueOf(loja.get(i));
                    }
                    
                
                    return new JWTAuthentication(email, getGrantedAuthorities(role), lojaLongList, usuario);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
    
    
    private List<GrantedAuthority> getGrantedAuthorities(List<String> role) {
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String s : role)
            authorities.add(new SimpleGrantedAuthority(s));
        return authorities;
    }

    public static String generateToken(String email, List<String> rolesGrupo, 
    		List<String> loja, String nomeUsuario, Long idUsuario, long expirationTime, String secret)
	{
        try {
            JWSSigner signer = new MACSigner(secret.getBytes());
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(email)
                    .expirationTime(new Date(System.currentTimeMillis() + expirationTime))
                    .claim("role", rolesGrupo)
                    .claim("loja", loja)
                    .claim("name", nomeUsuario)
                    .claim("usuario", idUsuario)
                    .build();

            JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(claims.toJSONObject()));

            jwsObject.sign(signer);
            return "Bearer " + jwsObject.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
    

}