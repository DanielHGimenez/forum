package br.com.dhg.testebexs.service;

import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;

public interface PerguntaService {

    Long publicar(String nomeUsuario, String pergunta);

    ExibicaoPerguntasPaginadoDTO buscarPaginado(Integer numeroPagina);

}
