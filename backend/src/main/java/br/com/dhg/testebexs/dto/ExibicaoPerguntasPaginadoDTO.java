package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExibicaoPerguntasPaginadoDTO {

    private List<PerguntaDTO> perguntas;
    private Integer paginaAtual;
    private Integer totalPaginas;

}
