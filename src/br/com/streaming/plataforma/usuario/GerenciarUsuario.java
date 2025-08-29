package br.com.streaming.plataforma.usuario;

import br.com.streaming.plataforma.excecoes.UsuarioJaCadastradoException;
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
}
