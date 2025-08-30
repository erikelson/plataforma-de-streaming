package br.com.streaming.plataforma.catalogo;

import java.util.Objects;

public abstract class Midia {

    private String titulo;
    private static String artista;
    private int duracaoSegundos;
    private Enum<?> genero;

    public Midia(String titulo, String artista, int duracaoSegundos, Enum<?> genero) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracaoSegundos = duracaoSegundos;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public static String getArtista() {
        return artista;
    }

    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public Enum<?> getGenero() {
        return genero;
    }

    public static String getDuracaoFormatado(int duracao) {
        int horas = duracao / 3600;
        int resto = duracao % 3600;
        int minutos = resto / 60;
        int segundos = resto % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Midia midia = (Midia) o;
        return Objects.equals(titulo, midia.titulo) && Objects.equals(artista, midia.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista);
    }

    @Override
    public String toString() {
        return "Título: " + titulo + " | Artista: " + artista + " | Duracao: " + getDuracaoFormatado(getDuracaoSegundos()) + " | Gênero: " + genero;
    }
}
