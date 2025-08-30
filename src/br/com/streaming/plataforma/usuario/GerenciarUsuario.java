package br.com.streaming.plataforma.usuario;

import br.com.streaming.plataforma.catalogo.Catalogo;
import br.com.streaming.plataforma.catalogo.Midia;
import br.com.streaming.plataforma.excecoes.NenhumUsuarioCadastradoException;
import br.com.streaming.plataforma.excecoes.NenhumaMidiaEncontradaException;
import br.com.streaming.plataforma.excecoes.UsuarioJaCadastradoException;
import br.com.streaming.plataforma.excecoes.UsuarioNaoEncontradoException;
import br.com.streaming.plataforma.excecoes.NenhumaPlaylistParaUsuarioException;
import br.com.streaming.plataforma.playlist.Playlist;
import br.com.streaming.plataforma.utilitario.Utilitarios;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GerenciarUsuario {
    private static Map<String, Usuario> usuariosPorEmail = new HashMap<>();

    public static void cadastrarUsuario() throws UsuarioJaCadastradoException {
        String nome = Utilitarios.inputString("Cadastrar Usuário", "Digite o nome do usuário:", false);
        String email = Utilitarios.inputString("Cadastrar Usuário", "Digite o email:", true);
        if (usuariosPorEmail.containsKey(email)) {
            throw new UsuarioJaCadastradoException("Email: " + email + " já possui cadastro!");
        }
        Usuario usuarioCadastrado = new Usuario(nome, email);
        usuariosPorEmail.put(email, usuarioCadastrado);
        Utilitarios.exibirMessagem("Usuário cadastrado com sucesso!");


    }

    public static void listarUsuarios() throws NenhumUsuarioCadastradoException {
        if (usuariosPorEmail.isEmpty()) {
            throw new NenhumUsuarioCadastradoException("Nenhum usuáro cadastrado.");
        }
        String usuarios = "Lista de Usuarios\n";
        for (Usuario usuario : usuariosPorEmail.values()) {
            usuarios += "\nNome: " + usuario.getNome() + " | Email: " + usuario.getEmail();
        }
        Utilitarios.exibirMessagem(usuarios);
    }

    public static void removerUsuario() throws NenhumUsuarioCadastradoException, UsuarioNaoEncontradoException {

        if (usuariosPorEmail.isEmpty()) {
            throw new NenhumUsuarioCadastradoException("Nenhum usuário cadastrado.");
        }
        String email = Utilitarios.inputString("Remover usuário", "Digite o email que deseja remover:", true);
        if (!usuariosPorEmail.containsKey(email)) {
            throw new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado!");
        } else {
            usuariosPorEmail.remove(email);
            Utilitarios.exibirMessagem("usuário com email: " + email + " Removido com sucesso! ");
        }
    }

    public static void criarPlaylistPorUsuario() throws UsuarioNaoEncontradoException, NenhumUsuarioCadastradoException {

        if (usuariosPorEmail.isEmpty()) {
            throw new NenhumUsuarioCadastradoException("Nenhum usuário cadastrado.");
        }
        String email = Utilitarios.inputString("Criar playlist", "Digite o email do usuario", true);

        if (!usuariosPorEmail.containsKey(email)) {
            throw new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado!");
        }
        Usuario usuario = usuariosPorEmail.get(email);

        String nomeDaPlaylist = Utilitarios.inputString("Criar playlist", "Digite o nome da playlist", true);

        Playlist novaPlaylist = new Playlist(nomeDaPlaylist);

        usuario.getPlaylist().put(nomeDaPlaylist, novaPlaylist);
    }

    public static void listarPlaylistPorUsuario() throws UsuarioNaoEncontradoException, NenhumUsuarioCadastradoException, NenhumaPlaylistParaUsuarioException {
        if (usuariosPorEmail.isEmpty()) {
            throw new NenhumUsuarioCadastradoException("Nenhum usuário cadastrado.");
        }
        String email = Utilitarios.inputString("Listar mídias da playlist", "Digite o email do usuario:", true);

        if (!usuariosPorEmail.containsKey(email)) {
            throw new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado!");
        }
        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario.getPlaylist().isEmpty()) {
            throw new NenhumaPlaylistParaUsuarioException("Nenhuma playlist cadastrada para o usuário com email " + email);
        }
        String playlistDoUsuario = "Usuario: " + usuario.getNome() + " | Email: " + usuario.getEmail();
        for (Playlist playlist : usuario.getPlaylist().values()) {
            playlistDoUsuario +=  " \n[Playlist: " + playlist.getNomeDaPlaylist() + "]\n" + playlist.getMusicasDaPlaylist();
        }
        Utilitarios.exibirMessagem(playlistDoUsuario);
    }

    public static void adicionaMidiaNaPlaylist() throws UsuarioNaoEncontradoException, NenhumaPlaylistParaUsuarioException, NenhumaMidiaEncontradaException {
        if (usuariosPorEmail.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Nenhum usuario cadastrado.");
        }

        String email = Utilitarios.inputString("Adicionar música na playlist", "Digite o email do usuario:", true);
        if (!usuariosPorEmail.containsKey(email)) {
            throw new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado!");
        }

        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario.getPlaylist().isEmpty()) {
            throw new NenhumaPlaylistParaUsuarioException("Nenhuma playlist cadastrada para o usuário com email " + email);
        }

        Set<Midia> midiasCadastradas = Catalogo.getMidias();
        if (midiasCadastradas.isEmpty()) {
            throw new NenhumaMidiaEncontradaException("Nenhuma mídia cadastrada!");
        }

        List<Playlist> listaPlaylists = new ArrayList<>(usuario.getPlaylist().values());
        String opcoes = "Escolha uma playlist:\n";
        for (int i = 0; i < listaPlaylists.size(); i++) {
            opcoes += "\n" + (i + 1) + " - " + listaPlaylists.get(i).getNomeDaPlaylist();
        }

        int opcaoEscolhida = Utilitarios.inputOpcaoInt("Adicionar música na playlist", opcoes + "\n\nEscolha uma opção:");
        if (opcaoEscolhida < 1 || opcaoEscolhida > listaPlaylists.size()) {
            Utilitarios.exibirMessagem("Opção inválida, tente novamente!");
        }
        Playlist playlistEscolhida;
        try {
            playlistEscolhida = listaPlaylists.get(opcaoEscolhida - 1);
        } catch (IndexOutOfBoundsException e) {
            Utilitarios.exibirMessagem("Valor inválido, tente novamente!!!");
            return;
        }
        List<Midia> listaMidias = new ArrayList<>(midiasCadastradas);
        String escolhaMusicas = "";
        for (int i = 0; i < listaMidias.size(); i++) {
            escolhaMusicas += "\n" + (i + 1) + " - " + listaMidias.get(i).toString();

        }
        int opcaoMusicaEscolhida = Utilitarios.inputOpcaoInt("Adicionar música na playlist", escolhaMusicas + "\n\nEscolha uma opção:");
        if (opcaoMusicaEscolhida < 1 || opcaoMusicaEscolhida > listaMidias.size()) {
            Utilitarios.exibirMessagem("Opção inválida, tente novamente!");
        }

        Midia midiaEscolhida;
        try {
            midiaEscolhida = listaMidias.get(opcaoMusicaEscolhida - 1);
        } catch (IndexOutOfBoundsException e) {
            Utilitarios.exibirMessagem("Valor inválido, tente novamente!!!");
            return;
        }
        boolean adicionar = playlistEscolhida.adicionarMidia(midiaEscolhida);
        if (adicionar) {
            Utilitarios.exibirMessagem("Música cadastrada com sucesso!");
        } else {
            Utilitarios.exibirMessagem("Música não cadastrada!");
        }
    }


    public static void removerMidiaNaPlaylist() throws UsuarioNaoEncontradoException, NenhumaPlaylistParaUsuarioException, NenhumaMidiaEncontradaException {
        if (usuariosPorEmail.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Nenhum usuario cadastrado.");
        }

        String email = Utilitarios.inputString("Remover mídia na playlist", "Digite o email do usuario:", true);
        if (!usuariosPorEmail.containsKey(email)) {
            throw new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado!");
        }

        Usuario usuario = usuariosPorEmail.get(email);
        if (usuario.getPlaylist().isEmpty()) {
            throw new NenhumaPlaylistParaUsuarioException("Nenhuma playlist cadastrada para o usuário com email " + email);
        }

        Set<Midia> midiasCadastradas = Catalogo.getMidias();
        if (midiasCadastradas.isEmpty()) {
            throw new NenhumaMidiaEncontradaException("Nenhuma mídia cadastrada!");
        }

        List<Playlist> listaPlaylists = new ArrayList<>(usuario.getPlaylist().values());
        String opcoes = "Escolha uma playlist:\n";
        for (int i = 0; i < listaPlaylists.size(); i++) {
            opcoes += "\n" + (i + 1) + " - " + listaPlaylists.get(i).getNomeDaPlaylist();
        }

        int opcaoEscolhida = Utilitarios.inputOpcaoInt("Remover música na playlist", opcoes + "\n\nEscolha uma opção:");
        if (opcaoEscolhida < 1 || opcaoEscolhida > listaPlaylists.size()) {
            Utilitarios.exibirMessagem("Opção inválida, tente novamente!");
        }
        Playlist playlistEscolhida;
        try {
            playlistEscolhida = listaPlaylists.get(opcaoEscolhida - 1);
        } catch (IndexOutOfBoundsException e) {
            Utilitarios.exibirMessagem("Valor inválido, tente novamente!!!");
            return;
        }
        List<Midia> listaMidias = new ArrayList<>(midiasCadastradas);
        String escolhaMusicas = "";
        for (int i = 0; i < listaMidias.size(); i++) {
            escolhaMusicas += "\n" + (i + 1) + " - " + listaMidias.get(i).toString();

        }
        int opcaoMusicaEscolhida = Utilitarios.inputOpcaoInt("Remover música na playlist", escolhaMusicas + "\n\nEscolha uma opção:");
        if (opcaoMusicaEscolhida < 1 || opcaoMusicaEscolhida > listaMidias.size()) {
            Utilitarios.exibirMessagem("Opção inválida, tente novamente!");
        }

        Midia midiaEscolhida;
        try {
            midiaEscolhida = listaMidias.get(opcaoMusicaEscolhida - 1);
        } catch (IndexOutOfBoundsException e) {
            Utilitarios.exibirMessagem("Valor inválido, tente novamente!!!");
            return;
        }
        boolean remover = playlistEscolhida.removerMidia(midiaEscolhida);
        if (remover) {
            Utilitarios.exibirMessagem("Música removida com sucesso!");
        } else {
            Utilitarios.exibirMessagem("Música não removida!");
        }
    }

}
