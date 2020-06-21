package br.com.dhg.testebexs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PerguntaNaoAchadaException extends RuntimeException {

    public PerguntaNaoAchadaException() {
        super("A pergunta não existe");
    }

    public PerguntaNaoAchadaException(Long id) {
        super(String.format("A pergunta com id %d não existe", id));
    }

}
