package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ExibicaoPerguntasPaginadoDTO extends PaginadoDTO {

    @Builder
    public ExibicaoPerguntasPaginadoDTO(List<PerguntaDTO> perguntas, Integer paginaAtual, Integer totalPaginas) {
        this.perguntas = perguntas;
        this.paginaAtual = paginaAtual;
        this.totalPaginas = totalPaginas;
    }

    private List<PerguntaDTO> perguntas;

}
