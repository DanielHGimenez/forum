package br.com.dhg.testebexs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CadastrarUsuarioDTO {

    @NotBlank(message = "nome de usuario não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = "senha não pode ser nula ou vazia")
    private String senha;

}
