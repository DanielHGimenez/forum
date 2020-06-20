package br.com.dhg.testebexs.repository;

import br.com.dhg.testebexs.model.Pergunta;
import br.com.dhg.testebexs.model.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Integer countByPergunta(Pergunta pergunta);

    Page<Resposta> findByPerguntaId(Long id, Pageable pageable);

}
