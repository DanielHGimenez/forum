package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerguntaDTO {

    private Long id;
    private String texto;
    private Integer quantidadeRespostas;

}
