package br.com.streaming.plataforma.excecoes;

public class EntradaInvalidaException extends RuntimeException {
    public EntradaInvalidaException(String message) {
        super(message);
    }
}
