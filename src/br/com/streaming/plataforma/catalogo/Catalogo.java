package br.com.streaming.plataforma.catalogo;

import br.com.streaming.plataforma.enums.GeneroMusica;
import br.com.streaming.plataforma.excecoes.NenhumaMidiaEncontradaException;
import br.com.streaming.plataforma.utilitario.Utilitarios;

import java.util.HashSet;
import java.util.Set;

public class Catalogo {
    private static Set<Midia> midias = new HashSet<>();

    public Catalogo() {
    }


    public static boolean adicionarMidia(Midia midia) {

        return midias.add(midia);
    }

    public static Set<Midia> getMidias() {
        return midias;
    }

    public static void listarMidias() {
        int totalMusicas = 0;
        int totalPodcasts = 0;
        int totalAudiobook = 0;
        String musicas = "Músicas:";
        String podcasts = "Podcasts:";
        String audiobooks = "Audiobooks:";
        for (Midia midia : midias) {
            if (midia instanceof Musica) {
                musicas += "\n  " + midia;
                totalMusicas++;
            } else if (midia instanceof Podcast) {
                podcasts += "\n  " + midia;
                totalPodcasts++;
            } else if (midia instanceof Audiobook) {
                audiobooks += "\n  " + midia;
                totalAudiobook++;
            }
        }
        if (totalMusicas == 0) {
            musicas = "Música:\n  Não existe música cadastrada";
        }
        if (totalPodcasts == 0) {
            podcasts = "Podcast:\n  Não existe podcast cadastrado";
        }
        if (totalAudiobook == 0) {
            audiobooks = "Audiobook:\n  Não existe audiobook cadastrado";
        }
        Utilitarios.exibirMessagem(musicas + "\n\n" + podcasts + "\n\n" + audiobooks);

    }

    public static boolean removerMidia(int tipo) throws NenhumaMidiaEncontradaException {
        if (midias.isEmpty()) {
            throw new NenhumaMidiaEncontradaException("Nenhuma mídia cadastrada!");
        }
        String tituloRemover = Utilitarios.inputString("Remover mídia do catálogo", "Digite o titulo da mídia que deseja remover:", true);

        Midia remover = null;

        for (Midia midia : midias) {
            if (midia.getTitulo().equalsIgnoreCase(tituloRemover) && midia instanceof Musica && tipo == 1) {
                remover = midia;
            } else if (midia.getTitulo().equalsIgnoreCase(tituloRemover) && midia instanceof Podcast && tipo == 2) {
                remover = midia;
            } else if (midia.getTitulo().equalsIgnoreCase(tituloRemover) && midia instanceof Audiobook && tipo == 3) {
                remover = midia;
            }
        }
        if (remover != null) {
            return midias.remove(remover);
        }

        return false;
    }

    public static String buscarMidia(int tipo) throws NenhumaMidiaEncontradaException {
        if (midias.isEmpty()) {
            throw new NenhumaMidiaEncontradaException("Nenhuma mídia cadastrada!");
        }
        String tipoNome = "";
        switch (tipo) {
            case 1:
                tipoNome = "título";
                break;
            case 2:
                tipoNome = "artista";
                break;
            case 3:
                tipoNome = "gênero";
                break;
            default:
                break;
        }

        String midiaEncontrada = "";

        if (tipo == 3) {
            GeneroMusica buscarMusica = Utilitarios.perguntarGeneroMusical("Escolha o gênero da música", "Escolha o gênero da música que deseja buscar:");
            for (Midia midia : midias) {
                if (midia.getGenero().equals(buscarMusica) && midia instanceof Musica) {
                    midiaEncontrada += "\n" + midia;
                }
            }
        } else {
            String buscarMusica = Utilitarios.inputString("Buscar música por " + tipoNome, "Digite o " + tipoNome + " da música que deseja buscar:", true);
            for (Midia midia : midias) {
                if (midia.getTitulo().equalsIgnoreCase(buscarMusica) && midia instanceof Musica && tipo == 1) {
                    midiaEncontrada += "\n" + midia;
                } else if (Midia.getArtista().equalsIgnoreCase(buscarMusica) && midia instanceof Musica && tipo == 2) {
                    midiaEncontrada += "\n" + midia;
                }
            }
        }
        return midiaEncontrada;
    }


}
