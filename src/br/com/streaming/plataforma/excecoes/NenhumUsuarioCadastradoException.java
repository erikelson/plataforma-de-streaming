package br.com.streaming.plataforma.excecoes;

public class NenhumUsuarioCadastradoException extends RuntimeException {
    public NenhumUsuarioCadastradoException(String message) {
        super(message);
    }
}
