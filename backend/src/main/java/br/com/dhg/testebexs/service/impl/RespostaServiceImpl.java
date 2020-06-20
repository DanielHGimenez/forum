package br.com.dhg.testebexs.service.impl;

import br.com.dhg.testebexs.configuration.property.ApplicationProperties;
import br.com.dhg.testebexs.dto.ExibicaoRespostasPaginadoDTO;
import br.com.dhg.testebexs.dto.RespostaDTO;
import br.com.dhg.testebexs.exception.NotFoundException;
import br.com.dhg.testebexs.model.Pergunta;
import br.com.dhg.testebexs.model.Resposta;
import br.com.dhg.testebexs.model.Usuario;
import br.com.dhg.testebexs.repository.PerguntaRepository;
import br.com.dhg.testebexs.repository.RespostaRepository;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import br.com.dhg.testebexs.service.RespostaService;
import br.com.dhg.testebexs.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaServiceImpl implements RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ApplicationProperties properties;

    @Override
    public Long publicar(Long idPergunta, String nomeUsuario, String resposta) {

        Optional<Pergunta> registroPergunta = perguntaRepository.findById(idPergunta);

        if (!registroPergunta.isPresent()) {
            throw new NotFoundException(String.format("A pergunta com id %d não existe", idPergunta));
        }

        Usuario usuario = usuarioRepository.findByNome(nomeUsuario).get();

        Resposta registroResposta = Resposta.builder()
                .texto(resposta)
                .dataCriacao(LocalDateTime.now(ZoneOffset.UTC))
                .pergunta(registroPergunta.get())
                .usuario(usuario)
                .build();

        respostaRepository.save(registroResposta);

        return registroResposta.getId();

    }

    @Override
    public ExibicaoRespostasPaginadoDTO buscarPaginado(Long idPergunta, Integer numeroPagina) {

        if (!perguntaRepository.existsById(idPergunta)) {
            throw new NotFoundException(String.format("A pergunta com id %d não existe", idPergunta));
        }

        Page<Resposta> paginaRegistrosRespotas =
                respostaRepository.findByPerguntaId(
                        idPergunta,
                        PageRequest.of(
                                ServiceUtil.corrigirNumeroPagina(numeroPagina),
                                properties.getQuantidadeRespostasPagina()
                        )
                );

        List<RespostaDTO> respostasDTOS = new LinkedList<RespostaDTO>();

        paginaRegistrosRespotas.forEach(resposta -> {

            RespostaDTO respostaDTO = RespostaDTO.builder()
                    .id(resposta.getId())
                    .texto(resposta.getTexto())
                    .build();

            respostasDTOS.add(respostaDTO);

        });

        return ExibicaoRespostasPaginadoDTO.builder()
                .respostas(respostasDTOS)
                .paginaAtual(numeroPagina)
                .totalPaginas(paginaRegistrosRespotas.getTotalPages())
                .build();

    }

}
