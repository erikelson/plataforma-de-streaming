package br.com.streaming.plataforma.catalogo;

import br.com.streaming.plataforma.enums.GeneroMusica;
import br.com.streaming.plataforma.utilitario.Utilitarios;

public class Musica extends Midia {

    public Musica(String titulo, String artista, int duracaoSegundos, Enum<?> genero) {
        super(titulo, artista, duracaoSegundos, genero);
    }

    public static void adicionarMusica(){
        String titulo = Utilitarios.inputString("Cadastrar Música", "Digite o título da música:", true);
        String artista = Utilitarios.inputString("Cadastrar Música", "Digite o nome do artista:", false);
        int duracao = Utilitarios.inputOpcaoInt("Cadastrar Música", "Digite a durancao (em segundos):");
        GeneroMusica generoEscolhido = Utilitarios.perguntarGeneroMusical("Cadastrar Música", "Escolha o gênero da música:");
        Musica musica = new Musica(titulo, artista, duracao, generoEscolhido);
        if(Catalogo.adicionarMidia(musica)){
            Utilitarios.exibirMessagem("Música adicionado!!!\n" + musica);
        } else {
            Utilitarios.exibirMessagem("Essa música já existe no catálogo!!!\n" + musica);
        }
    }

    public static void procurarMusicaPorTitulo(String titulo){
        Catalogo midias = new Catalogo();
        System.out.println(midias);
    }

    @Override
    public String toString() {
        return "Música --> Titulo: " + this.getTitulo() + " | Artista: " + this.getArtista() + " | Duração: " + this.getDuracaoFormatado() + " | Genero: " + this.getGenero();
    }
}
