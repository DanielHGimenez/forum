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
public class CadastroRespostaDTO {

    @NotEmpty(message = "A resposta não pode ser nula ou vazia")
    @Size(max = 250, message = "A resposta não pode exceder 250 caracteres de comprimento")
    private String resposta;

}
