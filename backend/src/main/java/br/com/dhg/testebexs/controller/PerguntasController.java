package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.CadastroPerguntaDTO;
import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;
import br.com.dhg.testebexs.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void publicarPergunta(Authentication authentication, @Valid @RequestBody CadastroPerguntaDTO perguntaDTO) {

        perguntaService.publicar((String) authentication.getPrincipal(), perguntaDTO.getPergunta());

    }

    @GetMapping
    public ExibicaoPerguntasPaginadoDTO exibirPerguntas(@RequestParam(required = false) Integer pagina) {

        if (pagina == null || pagina < 1)
            pagina = 1;

        return perguntaService.obterExibicoesPaginado(pagina);

    }

}