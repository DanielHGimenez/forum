package br.com.dhg.testebexs;

import br.com.dhg.testebexs.dto.CadastroPerguntaDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;

public class DadosFicticios {

    public static Authentication montarAutenticacaoFicticia() {
        return new UsernamePasswordAuthenticationToken("usuario teste", Collections.emptyList());
    }

    public static CadastroPerguntaDTO montarCadastroPerguntaFicticia(String pergunta) {
        return CadastroPerguntaDTO.builder()
            .pergunta(pergunta)
            .build();
    }

}
