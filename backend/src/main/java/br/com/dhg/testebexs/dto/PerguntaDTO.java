package br.com.dhg.testebexs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerguntaDTO {

    private Long id;
    private String texto;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantidadeRespostas;

}
