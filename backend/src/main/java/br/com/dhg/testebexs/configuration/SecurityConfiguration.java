package br.com.dhg.testebexs.configuration;

import br.com.dhg.testebexs.configuration.property.SecurityProperties;
import br.com.dhg.testebexs.security.AutenticacaoFilter;
import br.com.dhg.testebexs.security.AutorizacaoFilter;
import br.com.dhg.testebexs.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private SecurityProperties properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/perguntas").authenticated()
            .antMatchers(HttpMethod.POST, "/perguntas/respostas").authenticated()
            .anyRequest().permitAll()
            .and()
            .addFilterBefore(new AutenticacaoFilter(authenticationManager(), properties),
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new AutorizacaoFilter(properties),
                    UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
