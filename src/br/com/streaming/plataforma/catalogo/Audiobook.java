package br.com.streaming.plataforma.catalogo;

import br.com.streaming.plataforma.enums.GeneroAudiobook;
import br.com.streaming.plataforma.excecoes.NenhumaMidiaEncontradaException;
import br.com.streaming.plataforma.utilitario.Utilitarios;

public class Audiobook extends Midia {
    private String narrador;

    public Audiobook(String titulo, String autor, int duracaoSegundos, Enum<?> genero, String narrador) {
        super(titulo, autor, duracaoSegundos, genero);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }

    public String getAutor() {
        return Midia.getArtista();
    }

    public static void adicionarAudiobook() {

        String titulo = Utilitarios.inputString("Cadastrar Audiobook", "Digite o título do audiobook:", true);
        String autor = Utilitarios.inputString("Cadastrar Audiobook", "Digite o nome do(a) autor(a):", false);
        int duracao = Utilitarios.inputOpcaoInt("Cadastrar Audiobook", "Digite a durancao (em segundos):");
        String narrador = Utilitarios.inputString("Cadastrar Audiobook", "Digite o nome do(a) narrador(a):", false);
        GeneroAudiobook generoEscolhido = Utilitarios.perguntarGeneroAudibook("Cadastrar Audiobook", "Escolha o gênero do audibook:");

        Midia audiobook = new Audiobook(titulo, autor, duracao, generoEscolhido, narrador);
        if (Catalogo.adicionarMidia(audiobook)) {
            Utilitarios.exibirMessagem("Podcast adicionado!!!\n" + audiobook);
        } else {
            Utilitarios.exibirMessagem("Esse podcast já existe no catálogo!!!\n" + audiobook);
        }
    }

    public static void removerAudiobook(int tipo) {
        try {
            boolean removido = Catalogo.removerMidia(tipo);
            if (removido) {
                Utilitarios.exibirMessagem("Audiobook excluído do catálogo!!!");
            } else {
                Utilitarios.exibirMessagem("Não existe audiobook com esse autor no catálogo!!!");
            }
        } catch (NenhumaMidiaEncontradaException e) {
            Utilitarios.exibirMessagem(e.getMessage());
        }

    }


    @Override
    public String toString() {
        return "Audiobook --> Titulo: " + this.getTitulo() + " | Autor: " + this.getAutor() + " | Tempo: " + getDuracaoFormatado(getDuracaoSegundos()) + " | Genero: " + this.getGenero() + " | Narrado(a): " + this.getNarrador();
    }

}
