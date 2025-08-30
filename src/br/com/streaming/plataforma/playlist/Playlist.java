package br.com.streaming.plataforma.playlist;

import br.com.streaming.plataforma.catalogo.Midia;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private final String nomeDaPlaylist;
    private final List<Midia> listaMidias;

    public Playlist(String nomeDaPlaylist) {
        this.nomeDaPlaylist = nomeDaPlaylist;
        this.listaMidias = new ArrayList<>();
    }

    public String getNomeDaPlaylist() {
        return nomeDaPlaylist;
    }

    public List<Midia> getListaMidias() {
        return listaMidias;
    }

    public boolean adicionarMidia(Midia midia) {
        return listaMidias.add(midia);

    }

    public boolean removerMidia(Midia midia) {
        return listaMidias.remove(midia);
    }

    public void listarMidiasDaPlaylist() {
        for (Midia midia : listaMidias) {
            System.out.println(midia);
        }
    }

    public String getMusicasDaPlaylist() {
        if (listaMidias.isEmpty()) {
            return "[Sem mídias]";
        }

        String playlist = "";

        int totalDuracao = 0;
        for (Midia midia : listaMidias) {
            playlist += "\n" + midia;
            totalDuracao += midia.getDuracaoSegundos();
        }
        return playlist + "\n\nTempo total da playlist: " + Midia.getDuracaoFormatado(totalDuracao);
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(nomeDaPlaylist, playlist.nomeDaPlaylist);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nomeDaPlaylist);
    }

    @Override
    public String toString() {
        return "Nome da playlist: " + " - " + nomeDaPlaylist;
    }
}