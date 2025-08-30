package br.com.streaming.plataforma.catalogo;

import br.com.streaming.plataforma.enums.GeneroPodcast;
import br.com.streaming.plataforma.excecoes.NenhumaMidiaEncontradaException;
import br.com.streaming.plataforma.utilitario.Utilitarios;

public class Podcast extends Midia {
    private final String convidado;

    public Podcast(String titulo, String apresentador, int duracaoSegundos, GeneroPodcast genero, String convidado) {
        super(titulo, apresentador, duracaoSegundos, genero);
        this.convidado = convidado;

    }

    public String getApresentador() {
        return this.getArtista();
    }

    public String getConvidado() {
        return convidado;
    }

    public static void adicionarPodcast() {

        String titulo = Utilitarios.inputString("cadastrar podcast", "digite o título do podcast:", true);
        String apresentador = Utilitarios.inputString("cadastrar podcast", "digite o nome do apresentador:", true);

        String convidado = Utilitarios.inputString("cadastrar podcast", "digite o nome do convidado(a):", true);
        int duracao = Utilitarios.inputOpcaoInt("Cadastrar Podcast", "Digite a durancao (em segundos):");
        GeneroPodcast generoEscolhido = Utilitarios.perguntarGeneroPodcast("Cadastrar Podcast", "Escolha o gênero do podcast:");

        Podcast podcast = new Podcast(titulo, apresentador, duracao, generoEscolhido, convidado);
        if (Catalogo.adicionarMidia(podcast)) {
            Utilitarios.exibirMessagem("Podcast adicionado!!!\n" + podcast);
        } else {
            Utilitarios.exibirMessagem("Esse podcast já existe no catálogo!!!\n" + podcast);
        }
    }

    public static void removerPodcast(int tipo) {
        try {
            boolean removido = Catalogo.removerMidia(tipo);
            if (removido) {
                Utilitarios.exibirMessagem("Podcast excluída do catálogo!!!");
            } else {
                Utilitarios.exibirMessagem("Não existe podcast com esse título no catálogo!!!");
            }
        } catch (NenhumaMidiaEncontradaException e) {
            Utilitarios.exibirMessagem(e.getMessage());
        }

    }


    @Override
    public String toString() {
        return "Podcast --> Titulo: " + this.getTitulo() + " | Apresentador: " + this.getApresentador() + " | Convidado: " + this.getConvidado() + " | Tempo: " + this.getDuracaoFormatado(getDuracaoSegundos()) + " | Genero: " + this.getGenero();

    }

}

