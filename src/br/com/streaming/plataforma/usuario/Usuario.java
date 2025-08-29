package br.com.streaming.plataforma.usuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Usuario {
    private String nome;
    private String email;
    private Map<String,String> playlist;

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
        this.playlist = new HashMap<>();
    }

    public Map<String, String> getPlaylist() {
        return playlist;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, email);
    }
}
