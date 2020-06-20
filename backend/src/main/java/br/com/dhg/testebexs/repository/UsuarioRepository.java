package br.com.dhg.testebexs.repository;

import br.com.dhg.testebexs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNome(String nomeUsuario);

}
