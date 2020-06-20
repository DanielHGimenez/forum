package br.com.dhg.testebexs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "fk_pergunta_id")
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private Usuario usuario;

}
