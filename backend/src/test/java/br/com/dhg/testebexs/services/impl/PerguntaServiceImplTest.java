package br.com.dhg.testebexs.services.impl;

import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;
import br.com.dhg.testebexs.dto.PerguntaDTO;
import br.com.dhg.testebexs.infrastructure.property.ApplicationProperties;
import br.com.dhg.testebexs.model.Pergunta;
import br.com.dhg.testebexs.repository.PerguntaRepository;
import br.com.dhg.testebexs.service.UsuarioService;
import br.com.dhg.testebexs.service.impl.PerguntaServiceImpl;
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

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class PerguntaServiceImplTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerguntaServiceImpl perguntaService;

    @Autowired
    private PerguntaRepository repository;

    @Autowired
    private ApplicationProperties properties;

    private String nomeUsuario;
    private String senha;
    private String pergunta;

    private Integer quantidadeItensCadastrados;
    private Integer quantidadeItensPagina1;
    private Integer quantidadeItensPagina2;
    private Integer quantidadePaginasEsperadas;
    private Map<Long, String> perguntasEsperadas;

    @BeforeAll
    public void setup() {

        nomeUsuario = "usuario teste pergunta";
        senha = "senha ficticia";
        pergunta = "Pergunta ficticia";

       usuarioService.cadastrar(nomeUsuario, senha);

        quantidadeItensPagina1 = properties.getQuantidadePerguntasPagina();
        quantidadeItensPagina2 = properties.getQuantidadePerguntasPagina() - 2;
        quantidadeItensCadastrados = quantidadeItensPagina1 + quantidadeItensPagina2;
        quantidadePaginasEsperadas = 2;

        perguntasEsperadas = new LinkedHashMap<>();

        for (Integer i = 0; i < quantidadeItensCadastrados; i++) {
            String perguntaGerada = pergunta + " " + i.toString();
            Long perguntaId = perguntaService.publicar(nomeUsuario, perguntaGerada);

            perguntasEsperadas.put(perguntaId, perguntaGerada);
        }
    }

    @Test
    public void publicarComSucesso() {

        Long id = perguntasEsperadas.keySet().stream().findFirst().get();
        Pergunta registroPergunta = repository.findById(id).get();

        Assertions.assertNotNull(registroPergunta);
        Assertions.assertEquals(nomeUsuario, registroPergunta.getUsuario().getNome());

    }

    @Test
    public void buscarPaginadoRetornaNumeroPaginaCorreto() {

        Integer paginaRequisitada = 1;
        ExibicaoPerguntasPaginadoDTO exibicao = perguntaService.buscarPaginado(paginaRequisitada);

        Assertions.assertEquals(paginaRequisitada, exibicao.getPaginaAtual());

    }

    @Test
    public void buscarPaginadoRetornaQuantidadePerguntasEsperado() {

        ExibicaoPerguntasPaginadoDTO exibicaoPagina1 = perguntaService.buscarPaginado(1);
        ExibicaoPerguntasPaginadoDTO exibicaoPagina2 = perguntaService.buscarPaginado(2);

        Assertions.assertEquals(quantidadeItensPagina1, exibicaoPagina1.getPerguntas().size());
        Assertions.assertEquals(quantidadeItensPagina2, exibicaoPagina2.getPerguntas().size());

    }

    @Test
    public void buscarPaginadoRetornaQuantidadePaginasEsperado() {

        ExibicaoPerguntasPaginadoDTO exibicao = perguntaService.buscarPaginado(1);
        Assertions.assertEquals(quantidadePaginasEsperadas, exibicao.getTotalPaginas());

    }

    @Test
    public void buscarPaginadoRetornaPerguntasEsperadas() {

        List<Long> idPerguntasEsperadas = perguntasEsperadas.keySet().stream().collect(Collectors.toList());
        Collections.reverse(idPerguntasEsperadas);

        List<Long> idsEsperadosPagina1 = idPerguntasEsperadas.subList(0, quantidadeItensPagina1);
        List<Long> idsEsperadosPagina2 = idPerguntasEsperadas.subList(quantidadeItensPagina1, quantidadeItensCadastrados);

        List<String> textoPerguntasEsperadas = perguntasEsperadas.values().stream().collect(Collectors.toList());
        Collections.reverse(textoPerguntasEsperadas);

        List<String> perguntasEsperadasPagina1 = textoPerguntasEsperadas.subList(0, quantidadeItensPagina1);
        List<String> perguntasEsperadasPagina2 = textoPerguntasEsperadas.subList(quantidadeItensPagina1, quantidadeItensCadastrados);

        ExibicaoPerguntasPaginadoDTO exibicaoPagina1 = perguntaService.buscarPaginado(1);
        ExibicaoPerguntasPaginadoDTO exibicaoPagina2 = perguntaService.buscarPaginado(2);

        List<Long> idPerguntasCadastradasPagina1 =
                exibicaoPagina1.getPerguntas().stream().map(PerguntaDTO::getId).collect(Collectors.toList());

        List<String> perguntasCadastradasPagina1 =
                exibicaoPagina1.getPerguntas().stream().map(PerguntaDTO::getTexto).collect(Collectors.toList());

        List<Long> idPerguntasCadastradasPagina2 =
                exibicaoPagina2.getPerguntas().stream().map(PerguntaDTO::getId).collect(Collectors.toList());

        List<String> perguntasCadastradasPagina2 =
                exibicaoPagina2.getPerguntas().stream().map(PerguntaDTO::getTexto).collect(Collectors.toList());

        Assertions.assertEquals(idsEsperadosPagina1, idPerguntasCadastradasPagina1);
        Assertions.assertEquals(perguntasEsperadasPagina1, perguntasCadastradasPagina1);
        Assertions.assertEquals(idsEsperadosPagina2, idPerguntasCadastradasPagina2);
        Assertions.assertEquals(perguntasEsperadasPagina2, perguntasCadastradasPagina2);

    }

}
