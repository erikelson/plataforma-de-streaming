package br.com.streaming.plataforma.utilitario;

import br.com.streaming.plataforma.catalogo.Audiobook;
import br.com.streaming.plataforma.catalogo.Catalogo;
import br.com.streaming.plataforma.catalogo.Musica;
import br.com.streaming.plataforma.catalogo.Podcast;
import br.com.streaming.plataforma.excecoes.*;
import br.com.streaming.plataforma.usuario.GerenciarUsuario;

public class Menu {

    public static void exibirMenuPrincipal() {
        int opcao;
        do {
            opcao = Utilitarios.inputOpcaoInt("Menu Principal", "\n1 - Usuário\n2 - Catálogo de mídias\n3 - Gerenciar playlist\n4 - Sair\n\nEscolha uma opção: ");
            switch (opcao) {
                case 1:
                    exibirMenuUsuario();
                    break;
                case 2:
                    exibirMenuCatalogo();
                    break;
                case 3:
                    exibirMenuGerencimentoPlaylist();
                    break;
                case 4:
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");
            }
        } while (opcao != 4);
        Utilitarios.exibirMessagem("Saiu do sistema!");
    }

    public static void exibirMenuUsuario() {
        int opcao;
        do {
            opcao = Utilitarios.inputOpcaoInt("Menu Usuario", "\n1 - Cadastrar usuário\n2 - Listar usuários\n3 - Remover usuário\n4 - Retornar para o menu principal\n\nEscolha uma opção: ");
            switch (opcao) {
                case 1:
                    try {
                        GerenciarUsuario.cadastrarUsuario();
                    } catch (UsuarioJaCadastradoException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        GerenciarUsuario.listarUsuarios();
                    } catch (NenhumUsuarioCadastradoException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        GerenciarUsuario.removerUsuario();
                    } catch (NenhumUsuarioCadastradoException | UsuarioNaoEncontradoException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Retornar para o menu principal");
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");
            }
        } while (opcao != 4);
    }

    public static void exibirMenuCatalogo() {
        int opcao;
        do {
            opcao = Utilitarios.inputOpcaoInt("Menu Catálogo", "\n1 - Cadastrar mídia\n2 - Listar midia\n3 - Remover midia\n4 - Procurar música por título/artista/gênero\n5 - Retornar para o menu principal\n\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    exibirSubMenuCadastrarMidia();
                    break;
                case 2:
                    Catalogo.listarMidias();
                    break;
                case 3:
                    exibirSubMenuRemoverMidiaPorTitulo();
                    break;
                case 4:
                    exibirSubMenuProcurarMusica();
                    break;
                case 5:
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");
            }
        } while (opcao != 5);
    }

    public static void exibirSubMenuCadastrarMidia() {

        int opcao = Utilitarios.inputOpcaoInt("Cadastrar mídia", "\nEscolha o tipo de mídia que deseja cadastrar\n\n  1 - Música\n  2 - Podcast\n  3 - Audiobook\n\nEscolha uma opção:");

        switch (opcao) {
            case 1:
                Musica.adicionarMusica();
                break;
            case 2:
                Podcast.adicionarPodcast();
                break;
            case 3:
                Audiobook.adicionarAudiobook();
                break;
            default:
                Utilitarios.exibirMessagem("Tipo de mídia inválido, volte e tente novamente!");
        }

    }

    private static void exibirSubMenuRemoverMidiaPorTitulo() {
        int tipoMediaRemover = Utilitarios.inputOpcaoInt("Remover mídia", "\nEscolha o tipo de mídia que deseja remover\n\n  1 - Música\n  2 - Podcast\n  3 - Audiobook\n\nEscolha uma opção:");
        switch (tipoMediaRemover) {
            case 1:
                Musica.removerMusica(tipoMediaRemover);
                break;
            case 2:
                Podcast.removerPodcast(tipoMediaRemover);
                break;
            case 3:
                Audiobook.removerAudiobook(tipoMediaRemover);
                break;
            default:
                Utilitarios.exibirMessagem("Tipo de mídia inválido, volte e tente novamente!");
        }
    }

    private static void exibirSubMenuProcurarMusica() {
        int tipoMediaProcurada = Utilitarios.inputOpcaoInt("Procurar música no catálogo", "\nEscolha a opção que deseja procurar a música\n\n  1 - Buscar música por título\n  2 - Buscar música por artista\n  3 - Buscar música por gênero\n\nEscolha uma opção:");
        switch (tipoMediaProcurada) {
            case 1:
                Musica.procurarMusicaPorTituloArtistaGenero(tipoMediaProcurada);
                break;
            case 2:
                Musica.procurarMusicaPorTituloArtistaGenero(tipoMediaProcurada);
                break;
            case 3:
                Musica.procurarMusicaPorTituloArtistaGenero(tipoMediaProcurada);
                break;
            default:
                Utilitarios.exibirMessagem("Tipo de mídia inválido, volte e tente novamente!");
        }
    }


    private static void exibirMenuGerencimentoPlaylist() {
        int opcao;
        do {

            opcao = Utilitarios.inputOpcaoInt("Gerenciamento Playlist", "\n  1 - Criar playlist\n  2 - Listar playlist\n  3 - Adicionar mídia na playlist\n  4 - Remover mídia da playlist\n  5 - Retornar menu usuário\n\nEscolha uma opção:");

            switch (opcao) {
                case 1:
                    try {
                        GerenciarUsuario.criarPlaylistPorUsuario();
                    } catch (NenhumUsuarioCadastradoException | UsuarioNaoEncontradoException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        GerenciarUsuario.listarPlaylistPorUsuario();
                    } catch (NenhumUsuarioCadastradoException | UsuarioNaoEncontradoException |
                             NenhumaPlaylistParaUsuarioException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        GerenciarUsuario.adicionaMidiaNaPlaylist();
                    } catch (UsuarioNaoEncontradoException | NenhumaPlaylistParaUsuarioException |
                             NenhumaMidiaEncontradaException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        GerenciarUsuario.removerMidiaNaPlaylist();
                    } catch (UsuarioNaoEncontradoException | NenhumaPlaylistParaUsuarioException |
                             NenhumaMidiaEncontradaException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 5:
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");

            }

        } while (opcao != 5);
    }

}


