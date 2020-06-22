package br.com.dhg.testebexs.services.impl;

import br.com.dhg.testebexs.exception.NomeUsuarioJaCadastradoException;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import br.com.dhg.testebexs.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class UsuarioServiceImplTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private String nomeUsuario;
    private String senha;

    @BeforeAll
    public void setup() {

        nomeUsuario = "usuario ficticio";
        senha = "senha ficticia";

    }

    @Test
    public void cadastroComSucesso() {

        usuarioService.cadastrar(nomeUsuario, senha);
        Assertions.assertTrue(usuarioRepository.existsByNome(nomeUsuario));

    }

    @Test
    public void cadastrComErroNomeDeUsuarioJaUsado() {


        Assertions.assertThrows(NomeUsuarioJaCadastradoException.class, () -> {
            usuarioService.cadastrar(nomeUsuario, senha);
        });

    }

}
