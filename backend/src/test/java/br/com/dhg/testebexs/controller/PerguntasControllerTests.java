package br.com.dhg.testebexs.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static br.com.dhg.testebexs.DadosFicticios.montarAutenticacaoFicticia;
import static br.com.dhg.testebexs.DadosFicticios.montarCadastroPerguntaFicticia;

@SpringBootTest
@ActiveProfiles("test")
public class PerguntasControllerTests {

    @Autowired
    private PerguntasController controller;

    @Test
    void publicarPerguntaComErroPerguntaVaziaTest() {

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(montarAutenticacaoFicticia("usuario teste"), montarCadastroPerguntaFicticia(" "));
        });

    }

    @Test
    void publicarPerguntaComErroPerguntaNulaTest() {

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(montarAutenticacaoFicticia("usuario teste"), montarCadastroPerguntaFicticia(null));
        });

    }

}
