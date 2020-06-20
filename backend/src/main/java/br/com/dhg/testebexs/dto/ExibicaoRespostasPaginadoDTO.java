package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ExibicaoRespostasPaginadoDTO extends PaginadoDTO {

    @Builder
    public ExibicaoRespostasPaginadoDTO(List<RespostaDTO> respostas, Integer paginaAtual, Integer totalPaginas) {
        this.respostas = respostas;
        this.paginaAtual = paginaAtual;
        this.totalPaginas = totalPaginas;
    }

    private List<RespostaDTO> respostas;

}
