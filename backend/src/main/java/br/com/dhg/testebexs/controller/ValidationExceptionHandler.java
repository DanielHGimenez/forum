package br.com.dhg.testebexs.controller;

import br.com.dhg.testebexs.dto.ErroValidacaoCampoBodyDTO;
import br.com.dhg.testebexs.dto.Wrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErroValidacaoCampoBodyDTO> campos = new LinkedList<ErroValidacaoCampoBodyDTO>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {

            Boolean campoEncontrado = false;

            for (ErroValidacaoCampoBodyDTO erroValidacaoCampoBodyDTO : campos) {

                campoEncontrado = erroValidacaoCampoBodyDTO.getCampo().equals(fieldError.getField());

                if (campoEncontrado) {
                    erroValidacaoCampoBodyDTO.getMensagens().add(fieldError.getDefaultMessage());
                    break;
                }

            }

            if (!campoEncontrado) {

                List<String> mensagens = new LinkedList<String>();
                mensagens.add(fieldError.getDefaultMessage());

                ErroValidacaoCampoBodyDTO erroValidacaoCampoBodyDTO = ErroValidacaoCampoBodyDTO.builder()
                        .campo(fieldError.getField())
                        .mensagens(mensagens)
                        .build();

                campos.add(erroValidacaoCampoBodyDTO);

            }

        });

        return new ResponseEntity<Object>(
                new Wrapper<List<ErroValidacaoCampoBodyDTO>>("erros", campos),
                HttpStatus.BAD_REQUEST
        );

    }
}
