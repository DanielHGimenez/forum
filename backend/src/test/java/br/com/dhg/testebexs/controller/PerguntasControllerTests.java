package br.com.dhg.testebexs.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
public class PerguntasControllerTests {

    @Autowired
    private PerguntasController controller;

    @Test
    void publicarPerguntaComErroPerguntaVaziaTest() {

        String pergunta = " ";
        Integer idUsuario = 1;

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(idUsuario, pergunta);
        });

    }

    @Test
    void publicarPerguntaComErroPerguntaNulaTest() {

        String pergunta = null;
        Integer idUsuario = 1;

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(idUsuario, pergunta);
        });

    }

    @Test
    void publicarPerguntaComErroIdUsuarioNuloTest() {

        String pergunta = "pergunta ficticia";
        Integer idUsuario = null;

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(idUsuario, pergunta);
        });

    }

    @Test
    void publicarPerguntaComErroIdUsuarioNegativoTest() {

        String pergunta = "pergunta ficticia";
        Integer idUsuario = -1;

        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> {
            controller.publicarPergunta(idUsuario, pergunta);
        });

    }

}
