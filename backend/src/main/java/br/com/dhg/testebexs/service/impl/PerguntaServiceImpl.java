package br.com.dhg.testebexs.service.impl;

import br.com.dhg.testebexs.configuration.property.ApplicationProperties;
import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;
import br.com.dhg.testebexs.dto.PerguntaDTO;
import br.com.dhg.testebexs.model.Pergunta;
import br.com.dhg.testebexs.model.Usuario;
import br.com.dhg.testebexs.repository.PerguntaRepository;
import br.com.dhg.testebexs.repository.RespostaRepository;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import br.com.dhg.testebexs.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;

@Service
public class PerguntaServiceImpl implements PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void publicar(String nomeUsuario, String pergunta) {

        Usuario usuario = usuarioRepository.findByNome(nomeUsuario).get();

        Pergunta registroPergunta = Pergunta.builder()
                .texto(pergunta)
                .usuario(usuario)
                .dataCriacao(LocalDateTime.now(ZoneOffset.UTC))
                .build();

        perguntaRepository.save(registroPergunta);

    }

    @Override
    public ExibicaoPerguntasPaginadoDTO obterExibicoesPaginado(Integer numeroPagina) {

        Page<Pergunta> paginaPerguntas = perguntaRepository.findAll(
                PageRequest.of(
                        /*
                         * Numero da pagina começa em 0 no Spring Data enquanto na API começa em 1,
                         * e por isso subtraimos 1 para igualar ao valor correto.
                         */
                        numeroPagina - 1,
                        applicationProperties.getQuantidadePerguntaPagina()
                )
        );

        List<PerguntaDTO> perguntasDTO = new LinkedList<>();

        paginaPerguntas.forEach(pergunta -> {

            Integer quantidadeRespostas = respostaRepository.countByPergunta(pergunta);

            PerguntaDTO perguntaDTO = PerguntaDTO.builder()
                    .id(pergunta.getId())
                    .texto(pergunta.getTexto())
                    .quantidadeRespostas(quantidadeRespostas)
                    .build();

            perguntasDTO.add(perguntaDTO);

        });

        return ExibicaoPerguntasPaginadoDTO.builder()
                .perguntas(perguntasDTO)
                .paginaAtual(numeroPagina)
                .totalPaginas(paginaPerguntas.getTotalPages())
                .build();

    }

}
