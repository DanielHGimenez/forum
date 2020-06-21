package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.CadastroRespostaDTO;
import br.com.dhg.testebexs.dto.ExibicaoRespostasPaginadoDTO;
import br.com.dhg.testebexs.dto.Wrapper;
import br.com.dhg.testebexs.service.RespostaService;
import br.com.dhg.testebexs.util.ControllerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/perguntas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{idPergunta}/respostas")
    public ExibicaoRespostasPaginadoDTO buscarRespostas(
            @PathVariable("idPergunta") Long idPergunta,
            @RequestParam(value = "pagina", required = false) Integer pagina
    ) {

        logger.info("Normalizando o numero da pagina");
        pagina = ControllerUtil.normalizarNumeroPagina(pagina);

        return respostaService.buscarPaginado(idPergunta, pagina);

    }

    @PostMapping("/{idPergunta}/respostas")
    public Wrapper<Long> publicarResposta(
            @RequestAttribute("usuario") String nomeUsuario,
            @PathVariable("idPergunta") Long idPergunta,
            @Valid @RequestBody CadastroRespostaDTO cadastroRespostaDTO
    ) {

        Long idRespostaPublicada =
                respostaService.publicar(
                        idPergunta,
                        nomeUsuario,
                        cadastroRespostaDTO.getResposta()
                );

        return new Wrapper<Long>("id", idRespostaPublicada);

    }

}
