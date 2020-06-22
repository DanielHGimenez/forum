package br.com.dhg.testebexs.exception;

public class NomeUsuarioJaCadastradoException extends RuntimeException {

    public NomeUsuarioJaCadastradoException() {
        super("O nome de usuario ja esta sendo usado por outra conta");
    }

    public NomeUsuarioJaCadastradoException(String mensagem) {
        super(String.format("O nome de usuario \"%s\" ja esta sendo usado por outra conta", mensagem));
    }

}
