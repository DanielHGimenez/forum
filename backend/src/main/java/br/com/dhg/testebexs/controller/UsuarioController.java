package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.CadastrarUsuarioDTO;
import br.com.dhg.testebexs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/autenticacao")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody CadastrarUsuarioDTO cadastrarUsuarioDTO) {

        service.cadastrar(cadastrarUsuarioDTO.getNome(), cadastrarUsuarioDTO.getSenha());

    }

}
