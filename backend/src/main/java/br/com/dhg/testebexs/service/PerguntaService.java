package br.com.dhg.testebexs.service;

import br.com.dhg.testebexs.dto.ExibicaoPerguntasPaginadoDTO;

public interface PerguntaService {

    void publicar(String nomeUsuario, String pergunta);

    ExibicaoPerguntasPaginadoDTO obterExibicoesPaginado(Integer numeroPagina);

}
