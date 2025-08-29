package br.com.streaming.plataforma.usuario;

import br.com.streaming.plataforma.excecoes.NenhumUsuarioCadastradoException;
import br.com.streaming.plataforma.excecoes.UsuarioJaCadastradoException;
import br.com.streaming.plataforma.excecoes.UsuarioNaoEncontradoException;
import br.com.streaming.plataforma.utilitario.Utilitarios;

import java.util.HashMap;
import java.util.Map;

public class GerenciarUsuario {
    private static Map<String, Usuario> usuariosPorEmail = new HashMap<>();

    public static void cadastrarUsuario() throws UsuarioJaCadastradoException {
        String nome = Utilitarios.inputString("Cadastrar Usuário", "Digite o nome do usuário:", false);
        String email = Utilitarios.inputString("Cadastrar Usuário", "Digite o email:", true);
        if(usuariosPorEmail.containsKey(email)){
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
}}
