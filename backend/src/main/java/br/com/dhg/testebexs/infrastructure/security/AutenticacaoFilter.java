package br.com.dhg.testebexs.infrastructure.security;

import br.com.dhg.testebexs.infrastructure.property.SecurityProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

public class AutenticacaoFilter extends UsernamePasswordAuthenticationFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private SecurityProperties properties;
    private AuthenticationManager authenticationManager;

    public AutenticacaoFilter(AuthenticationManager authenticationManager, SecurityProperties properties) {
        this.authenticationManager = authenticationManager;
        this.properties = properties;

        setFilterProcessesUrl(properties.getLoginURL());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        String nomeUsuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        logger.debug("Autenticando usuario {}", nomeUsuario);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(nomeUsuario, senha, Collections.emptyList());

        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException, ServletException {

        User usuario = (User) authResult.getPrincipal();
        logger.debug("Usuario {} autenticado com sucesso", usuario.getUsername());

        byte[] signingKey = properties.getTokenSecret().getBytes();
        String tokenType = properties.getTokenType();
        String tokenIssuer = properties.getTokenIssuer();
        String tokenAudience = properties.getTokenAudience();
        String tokenSubject = usuario.getUsername();
        Long expirationMilis = properties.getTokenExpiration();

        Instant expirationTime = Instant.now(Clock.systemUTC()).plusMillis(expirationMilis);
        Date expirationDate = Date.from(expirationTime);

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", tokenType)
                .setIssuer(tokenIssuer)
                .setAudience(tokenAudience)
                .setSubject(tokenSubject)
                .setExpiration(expirationDate)
                .compact();

        String nomeHeader = properties.getHeader();
        String prefixo = properties.getPrefix();

        response.addHeader(nomeHeader, prefixo + token);

    }

}
