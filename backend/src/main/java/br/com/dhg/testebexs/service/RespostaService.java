package br.com.dhg.testebexs.service;

import br.com.dhg.testebexs.dto.ExibicaoRespostasPaginadoDTO;
import br.com.dhg.testebexs.dto.RespostaDTO;

import java.util.List;

public interface RespostaService {

    Long publicar(Long idPergunta, String nomeUsuario, String resposta);

    ExibicaoRespostasPaginadoDTO buscarPaginado(Long idPergunta, Integer numeroPagina);

}
