package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RespostaDTO {

    private Long id;
    private String texto;

}
