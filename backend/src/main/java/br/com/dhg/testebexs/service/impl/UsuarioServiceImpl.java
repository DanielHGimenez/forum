package br.com.dhg.testebexs.service.impl;

import br.com.dhg.testebexs.exception.NomeUsuarioJaCadastradoException;
import br.com.dhg.testebexs.model.Usuario;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import br.com.dhg.testebexs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void cadastrar(String nomeUsuario, String senha) {

        if (repository.existsByNome(nomeUsuario)) {
            throw new NomeUsuarioJaCadastradoException(nomeUsuario);
        }

        Usuario usuario = Usuario.builder()
                .nome(nomeUsuario)
                .senha(passwordEncoder.encode(senha))
                .build();

        repository.save(usuario);

    }

}
