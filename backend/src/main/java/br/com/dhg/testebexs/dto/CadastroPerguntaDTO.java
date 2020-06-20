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

    @NotEmpty(message = "Valor da pergunta não pode ser nulo ou vazio")
    @Size(max = 250, message = "Pergunta excedeu 250 caracteres")
    private String pergunta;

}
