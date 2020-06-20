package br.com.dhg.testebexs.security;

import br.com.dhg.testebexs.model.Usuario;
import br.com.dhg.testebexs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = repository.findByNome(username);

        if (!usuarioOptional.isPresent()) {
            throw new UsernameNotFoundException("O usuario não foi encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        String password = usuario.getSenha();

        return new User(username, password, Collections.emptyList());

    }

}
