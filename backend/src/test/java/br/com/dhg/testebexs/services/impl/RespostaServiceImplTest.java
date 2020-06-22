package br.com.dhg.testebexs.services.impl;

import br.com.dhg.testebexs.dto.ExibicaoRespostasPaginadoDTO;
import br.com.dhg.testebexs.dto.RespostaDTO;
import br.com.dhg.testebexs.exception.PerguntaNaoAchadaException;
import br.com.dhg.testebexs.infrastructure.property.ApplicationProperties;
import br.com.dhg.testebexs.model.Resposta;
import br.com.dhg.testebexs.repository.RespostaRepository;
import br.com.dhg.testebexs.service.PerguntaService;
import br.com.dhg.testebexs.service.UsuarioService;
import br.com.dhg.testebexs.service.impl.RespostaServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest()
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class RespostaServiceImplTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private RespostaServiceImpl respostaService;

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private ApplicationProperties properties;

    private String nomeUsuario;
    private String senha;
    private String pergunta;
    private Long idPergunta;
    private String resposta;

    private Integer quantidadeItensCadastrados;
    private Integer quantidadeItensPagina1;
    private Integer quantidadeItensPagina2;
    private Integer quantidadePaginasEsperadas;
    private Map<Long, String> respostasEsperadas;

    @BeforeAll
    public void setup() {

        nomeUsuario = "usuario teste resposta";
        senha = "senha ficticia";
        pergunta = "Pergunta ficticia";
        resposta = "Resposta ficticia";

        usuarioService.cadastrar(nomeUsuario, senha);

        quantidadeItensPagina1 = properties.getQuantidadeRespostasPagina();
        quantidadeItensPagina2 = properties.getQuantidadeRespostasPagina() - 2;
        quantidadeItensCadastrados = quantidadeItensPagina1 + quantidadeItensPagina2;
        quantidadePaginasEsperadas = 2;

        idPergunta = perguntaService.publicar(nomeUsuario, pergunta);

        respostasEsperadas = new LinkedHashMap<>();
        for (Integer i = 0; i < quantidadeItensCadastrados; i++) {
            String respostaGerada = pergunta + " " + i.toString();
            Long perguntaId = respostaService.publicar(idPergunta, nomeUsuario, respostaGerada);

            respostasEsperadas.put(perguntaId, respostaGerada);
        }
    }

    @Test
    public void publicarRespostaComSucesso() {

        Long idResposta = respostasEsperadas.keySet().stream().findFirst().get();
        Resposta registroResposta = repository.findById(idResposta).get();

        Assertions.assertNotNull(registroResposta);
        Assertions.assertEquals(nomeUsuario, registroResposta.getUsuario().getNome());

    }

    @Test
    public void publicarRespostaErroPerguntaNaoExiste() {

        Assertions.assertThrows(PerguntaNaoAchadaException.class, () -> {
            respostaService.publicar(1000000L, nomeUsuario, resposta);
        });

    }

    @Test
    public void buscarPaginadoRetornaNumeroPaginaCorreto() {

        Integer paginaRequisitada = 1;
        ExibicaoRespostasPaginadoDTO exibicao = respostaService.buscarPaginado(idPergunta, paginaRequisitada);

        Assertions.assertEquals(paginaRequisitada, exibicao.getPaginaAtual());

    }

    @Test
    public void buscarPaginadoRetornaQuantidadeRespostasEsperado() {

        ExibicaoRespostasPaginadoDTO exibicaoPagina1 = respostaService.buscarPaginado(idPergunta, 1);
        ExibicaoRespostasPaginadoDTO exibicaoPagina2 = respostaService.buscarPaginado(idPergunta, 2);

        Assertions.assertEquals(quantidadeItensPagina1, exibicaoPagina1.getRespostas().size());
        Assertions.assertEquals(quantidadeItensPagina2, exibicaoPagina2.getRespostas().size());

    }

    @Test
    public void buscarPaginadoRetornaQuantidadePaginasEsperado() {

        ExibicaoRespostasPaginadoDTO exibicao = respostaService.buscarPaginado(idPergunta, 1);
        Assertions.assertEquals(quantidadePaginasEsperadas, exibicao.getTotalPaginas());

    }

    @Test
    public void buscarPaginadoRetornaPerguntasEsperadas() {

        List<Long> idRespostasEsperadas = respostasEsperadas.keySet().stream().collect(Collectors.toList());
        Collections.reverse(idRespostasEsperadas);

        List<Long> idsEsperadosPagina1 = idRespostasEsperadas.subList(0, quantidadeItensPagina1);
        List<Long> idsEsperadosPagina2 = idRespostasEsperadas.subList(quantidadeItensPagina1, quantidadeItensCadastrados);

        List<String> textoRespostasEsperadas = respostasEsperadas.values().stream().collect(Collectors.toList());
        Collections.reverse(textoRespostasEsperadas);

        List<String> perguntasEsperadasPagina1 = textoRespostasEsperadas.subList(0, quantidadeItensPagina1);
        List<String> perguntasEsperadasPagina2 = textoRespostasEsperadas.subList(quantidadeItensPagina1, quantidadeItensCadastrados);

        ExibicaoRespostasPaginadoDTO exibicaoPagina1 = respostaService.buscarPaginado(idPergunta,1);
        ExibicaoRespostasPaginadoDTO exibicaoPagina2 = respostaService.buscarPaginado(idPergunta, 2);

        List<Long> idPerguntasCadastradasPagina1 =
                exibicaoPagina1.getRespostas().stream().map(RespostaDTO::getId).collect(Collectors.toList());

        List<String> perguntasCadastradasPagina1 =
                exibicaoPagina1.getRespostas().stream().map(RespostaDTO::getTexto).collect(Collectors.toList());

        List<Long> idPerguntasCadastradasPagina2 =
                exibicaoPagina2.getRespostas().stream().map(RespostaDTO::getId).collect(Collectors.toList());

        List<String> perguntasCadastradasPagina2 =
                exibicaoPagina2.getRespostas().stream().map(RespostaDTO::getTexto).collect(Collectors.toList());

        Assertions.assertEquals(idsEsperadosPagina1, idPerguntasCadastradasPagina1);
        Assertions.assertEquals(perguntasEsperadasPagina1, perguntasCadastradasPagina1);
        Assertions.assertEquals(idsEsperadosPagina2, idPerguntasCadastradasPagina2);
        Assertions.assertEquals(perguntasEsperadasPagina2, perguntasCadastradasPagina2);

    }

}
