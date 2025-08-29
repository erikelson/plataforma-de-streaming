package br.com.streaming.plataforma.excecoes;

public class UsuarioJaCadastradoException extends Exception {
    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}
