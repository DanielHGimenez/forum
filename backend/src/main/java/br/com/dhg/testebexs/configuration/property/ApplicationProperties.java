package br.com.dhg.testebexs.configuration.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application-${spring.profiles.active}.properties")
})
@Component
@Getter
public class ApplicationProperties {

    @Value("${pergunta.quantidade.pagina}")
    private Integer quantidadePerguntaPagina;

}
