package br.com.dhg.testebexs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErroValidacaoCampoBodyDTO {

    private String campo;
    private List<String> mensagens;

}
