package br.com.streaming.plataforma.utilitario;

import br.com.streaming.plataforma.excecoes.UsuarioJaCadastradoException;
import br.com.streaming.plataforma.usuario.GerenciarUsuario;

public class Menu {

    public static void exibirMenuPrincipal(){
       int opcao;
       do {
           opcao = Utilitarios.inputOpcaoInt("Menu Principal", "\n1 - Usuário\n2 - Catálogo de mídias\n3 - Gerenciar playlist\n4 - Sair\n\nEscolha uma opção: ");
           switch (opcao) {
               case 1:
                   System.out.println("Exibir menu usuario");
                   exibirMenuUsuario();
                   break;
               case 2:
                   System.out.println("Exibir menu catalogo");
                   exibirMenuCatalogo();
                   break;
               case 3:
                   System.out.println("Exibir menu playlist");
                   break;
               case 4:
                   break;
               default:
                   Utilitarios.exibirMessagem("Opção inválida, tente novamente");
           }
       } while (opcao != 4);
        Utilitarios.exibirMessagem("Saiu do sistema!");
    }

    public static void exibirMenuUsuario(){
        int opcao;
        do {
            opcao = Utilitarios.inputOpcaoInt("Menu Usuario", "\n1 - Cadastrar usuário\n2 - Listar usuários\n3 - Remover usuário\n4 - Retornar para o menu principal\n\nEscolha uma opção: ");
            switch (opcao) {
                case 1:
                    try{
                        GerenciarUsuario.cadastrarUsuario();
                    } catch (UsuarioJaCadastradoException e) {
                        Utilitarios.exibirMessagem(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Listar usuarios");
                    break;
                case 3:
                    System.out.println("Remover usuario");
                    break;
                case 4:
                    System.out.println("Retornar para o menu principal");
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");
            }
        } while (opcao != 4);
    }

    public static void exibirMenuCatalogo(){
        int opcao;
        do {
            opcao = Utilitarios.inputOpcaoInt("Menu Catálogo", "\n1 - Cadastrar mídia\n2 - Listar midia\n3 - Remover midia\n4 - Procurar música por título/artista/gênero\n5 - Retornar para o menu principal\n\nEscolha uma opção: ");

            switch (opcao) {
                case 1:
                    System.out.println("mídia");
                    break;
                case 2:
                    System.out.println("Listar mídias");
                    break;
                case 3:
                    System.out.println("Remover por titulo");
                    break;
                case 4:
                    System.out.println("Procurar música");
                    break;
                case 5:
                    break;
                default:
                    Utilitarios.exibirMessagem("Opção inválida, tente novamente");
            }
        } while (opcao != 5);
    }

}
