package br.com.dhg.testebexs.exception.handler;

import br.com.dhg.testebexs.dto.Wrapper;
import br.com.dhg.testebexs.exception.PerguntaNaoAchadaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(PerguntaNaoAchadaException.class)
    @ResponseBody
    public Wrapper<String> handleNotFound(PerguntaNaoAchadaException ex) {
        return new Wrapper<String>("erro", ex.getMessage());
    }

}
