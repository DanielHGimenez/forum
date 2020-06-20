package br.com.dhg.testebexs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CadastrarUsuarioDTO {

    @NotBlank(message = "O nome de usuario não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    private String senha;

}
