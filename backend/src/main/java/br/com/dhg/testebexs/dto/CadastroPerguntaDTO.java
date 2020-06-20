package br.com.dhg.testebexs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroPerguntaDTO {

    @NotEmpty(message = "A pergunta não pode ser nula ou vazia")
    @Size(max = 250, message = "A pergunta não pode exceder 250 caracteres de comprimento")
    private String pergunta;

}
