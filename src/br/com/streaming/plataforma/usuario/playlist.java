
import br.com.streaming.musicastreaming.catalogo.Midia;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class playlist{
    private final String nomeDaPlaylist;
    private final List<Midia> listaMidias;

    public playlist(String nomeDaPlaylist){
        this.nomeDaPlaylist = nomeDaPlaylist;
        this.listaMidias = new ArrayList<>();
    }

    public String getNomeDaPlaylist(){
        return nomeDaPlaylist;
    }
    public List<Midia> getListaMidias(){
        return listaMidias;
    }
    public boolean adicionarMidia(Midia midia){
        return listaMidias.add(midia);

    }
    public boolean removerMidia(Midia midia){
        return listaMidias.remove(midia);
    }

    public void listarMidiasDaPlaylist(){
        for (Midia midia : listaMidias) {
            System.out.println(midia);
        }
    }
    @Override
    public boolean equals(Object o){
        if (o == null || getClass() != o.getClass()) return false;
        playlist playlist = (playlist) o;
        return Objects.equals(nomeDaPlaylist, playlist.nomeDaPlaylist);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(nomeDaPlaylist);
    }
    @Override
    public String toString() {
        return "Playlist{" + "nomeDaPlaylist='" + nomeDaPlaylist + '\'' + ", listaMidias=" + listaMidias + '}';
    }
}
