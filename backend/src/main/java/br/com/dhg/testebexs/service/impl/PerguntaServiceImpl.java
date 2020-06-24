package br.com.dhg.testebexs.service.impl;

import br.com.dhg.testebexs.exception.PerguntaNaoAchadaException;
import br.com.dhg.testebexs.infrastructure.property.ApplicationProperties;
import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;
import br.com.dhg.testebexs.dto.PerguntaDTO;
import br.com.dhg.testebexs.model.Pergunta;
import br.com.dhg.testebexs.model.Usuario;
import br.com.dhg.testebexs.repository.PerguntaRepository;
import br.com.dhg.testebexs.repository.RespostaRepository;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import br.com.dhg.testebexs.service.PerguntaService;
import br.com.dhg.testebexs.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Long publicar(String nomeUsuario, String pergunta) {

        logger.info("Procurando usuario");
        Usuario usuario = usuarioRepository.findByNome(nomeUsuario).get();

        logger.info("Montando pergunta");
        Pergunta registroPergunta = Pergunta.builder()
                .texto(pergunta)
                .usuario(usuario)
                .dataCriacao(LocalDateTime.now(ZoneOffset.UTC))
                .build();

        logger.info("Salvando pergunta");
        perguntaRepository.save(registroPergunta);

        logger.debug("Publicação gerada. Id da publicação: {}", registroPergunta.getId());
        return registroPergunta.getId();

    }

    @Override
    public ExibicaoPerguntasPaginadoDTO buscarPaginado(Integer numeroPagina) {

        logger.info("Procurando perguntas");
        Page<Pergunta> paginaPerguntas =
            perguntaRepository.findAll(
                    PageRequest.of(
                            ServiceUtil.corrigirNumeroPagina(numeroPagina),
                            applicationProperties.getQuantidadePerguntasPagina(),
                            Sort.by("dataCriacao").descending()
                    )
            );

        List<PerguntaDTO> perguntasDTO = new LinkedList<>();

        logger.info("Montando lista de perguntas");
        paginaPerguntas.forEach(pergunta -> {

            Integer quantidadeRespostas = respostaRepository.countByPergunta(pergunta);

            PerguntaDTO perguntaDTO = PerguntaDTO.builder()
                    .id(pergunta.getId())
                    .texto(pergunta.getTexto())
                    .quantidadeRespostas(quantidadeRespostas)
                    .build();

            perguntasDTO.add(perguntaDTO);

        });

        logger.info("Montando retorno");
        return ExibicaoPerguntasPaginadoDTO.builder()
                .perguntas(perguntasDTO)
                .paginaAtual(numeroPagina)
                .totalPaginas(paginaPerguntas.getTotalPages())
                .build();

    }

    @Override
    public PerguntaDTO buscar(Long idPergunta) {

        Optional<Pergunta> registroPergunta = perguntaRepository.findById(idPergunta);

        if (!registroPergunta.isPresent()) {
            throw new PerguntaNaoAchadaException(idPergunta);
        }

        Pergunta pergunta = registroPergunta.get();

        return PerguntaDTO.builder()
                .id(pergunta.getId())
                .texto(pergunta.getTexto())
                .build();

    }


}
