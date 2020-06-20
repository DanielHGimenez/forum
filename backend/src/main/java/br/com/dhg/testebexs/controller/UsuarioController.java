package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.CadastrarUsuarioDTO;
import br.com.dhg.testebexs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/autenticacao")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public void cadastrar(@Valid @RequestBody CadastrarUsuarioDTO cadastrarUsuarioDTO) {

        service.cadastrar(cadastrarUsuarioDTO.getNome(), cadastrarUsuarioDTO.getSenha());

    }

}
