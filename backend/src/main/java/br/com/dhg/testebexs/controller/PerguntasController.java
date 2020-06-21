package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.CadastroPerguntaDTO;
import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;
import br.com.dhg.testebexs.dto.Wrapper;
import br.com.dhg.testebexs.service.PerguntaService;
import br.com.dhg.testebexs.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/perguntas")
public class PerguntasController {

    @Autowired
    private PerguntaService perguntaService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Wrapper<Long> publicarPergunta(
            @RequestAttribute("usuario") String nomeUsuario,
            @Valid @RequestBody CadastroPerguntaDTO perguntaDTO
    ) {

        Long idNovaPergunta = perguntaService.publicar(
                nomeUsuario,
                perguntaDTO.getPergunta()
        );

        return new Wrapper<Long>("id", idNovaPergunta);

    }

    @GetMapping
    public ExibicaoPerguntasPaginadoDTO buscarPaginaPerguntas(@RequestParam(required = false) Integer pagina) {

        logger.info("Normalizando o numero da pagina");
        pagina = ControllerUtil.normalizarNumeroPagina(pagina);

        return perguntaService.buscarPaginado(pagina);

    }

}
