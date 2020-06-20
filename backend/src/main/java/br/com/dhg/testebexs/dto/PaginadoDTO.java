package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

@Data
public abstract class PaginadoDTO {

    protected Integer paginaAtual;
    protected Integer totalPaginas;

}
