package br.com.dhg.testebexs.configuration.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@PropertySources({
        @PropertySource("application.properties"),
        @PropertySource("application-${spring.profiles.active}.properties")
})
@Component
@Getter
public class SecurityProperties {

    @Value("${auth.login.url}")
    private String loginURL;

    @Value("${auth.token.secret}")
    private String tokenSecret;

    @Value("${auth.token.type}")
    private String tokenType;

    @Value("${auth.token.issuer}")
    private String tokenIssuer;

    @Value("${auth.token.audience}")
    private String tokenAudience;

    @Value("${auth.token.expirationMilis}")
    private Long tokenExpiration;

    @Value("${auth.header}")
    private String header;

    @Value("${auth.prefix} ")
    private String prefix;

}