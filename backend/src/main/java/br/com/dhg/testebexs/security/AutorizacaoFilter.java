package br.com.dhg.testebexs.security;

import br.com.dhg.testebexs.configuration.property.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AutorizacaoFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private SecurityProperties properties;

    public AutorizacaoFilter(SecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        try {

            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

            if (authentication != null) {
                logger.info("Usuario logado");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException exception) {
            logger.info(exception.getMessage());
        }
        finally {
            chain.doFilter(request, response);
        }

    }

    private boolean authorizationHeaderIsValid(String authorizationHeader) {
        return authorizationHeader != null
                && !authorizationHeader.isEmpty()
                && authorizationHeader.startsWith(properties.getPrefix());
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(properties.getHeader());
        UsernamePasswordAuthenticationToken authenticationToken = null;

        if (authorizationHeaderIsValid(token)) {

            String prefixo = properties.getPrefix();
            byte[] secret = properties.getTokenSecret().getBytes();

            Jws<Claims> parsedToken = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token.replace(properties.getPrefix(), ""));

            String username = parsedToken
                    .getBody()
                    .getSubject();

            if (!username.isEmpty()) {
                authenticationToken =
                        new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            }

        }

        return authenticationToken;

    }

}
