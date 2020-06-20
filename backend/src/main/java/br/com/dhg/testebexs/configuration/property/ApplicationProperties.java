package br.com.dhg.testebexs.configuration.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class ApplicationProperties {

    @Value("${pergunta.quantidade.pagina}")
    private Integer quantidadePerguntasPagina;

    @Value("${resposta.quantidade.pagina}")
    private Integer quantidadeRespostasPagina;

}
