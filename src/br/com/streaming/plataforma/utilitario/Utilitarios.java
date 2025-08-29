package br.com.streaming.plataforma.utilitario;

import br.com.streaming.plataforma.enums.GeneroAudiobook;
import br.com.streaming.plataforma.enums.GeneroMusica;
import br.com.streaming.plataforma.excecoes.EntradaInvalidaException;

import javax.swing.JOptionPane;

public class Utilitarios {

    public static void exibirMessagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, null, JOptionPane.PLAIN_MESSAGE);

    }

    public static int inputOpcaoInt(String tituloMenu, String menu) {
        while (true) {
            String opcaoStr = JOptionPane.showInputDialog(null, menu, tituloMenu, JOptionPane.PLAIN_MESSAGE);

            try {
                opcaoStr = opcaoStr.trim();
                int opcao = Integer.parseInt(opcaoStr);
                if (tituloMenu.contains("Cadastrar Audibook") && opcao < 1 || tituloMenu.contains("Cadastrar Música") && opcao < 1 || tituloMenu.contains("Cadastrar Podcast") && opcao < 1) {
                    throw new EntradaInvalidaException("O valor deve ser acima de 0");
                }
                return opcao;
            } catch (NumberFormatException e) {
                if (tituloMenu.contains("Cadastrar Música")) {
                    exibirMessagem("Digite um valor válido!");
                } else {
                    exibirMessagem("Você deve escolher uma opção válida!");
                }

            } catch (NullPointerException e) {
                exibirMessagem("Você não pode cancelar/fechar");
            } catch (EntradaInvalidaException e) {
                exibirMessagem(e.getMessage());
            }
        }


    }

    public static String inputString(String titulo, String perguntar, boolean permitirNumero) {
        while (true) {
            try {
                String opcao = JOptionPane.showInputDialog(null, perguntar, titulo, JOptionPane.PLAIN_MESSAGE);

                if (opcao == null || opcao.isBlank()) {
                    throw new EntradaInvalidaException("Você deve preencher o campo!");
                }

                if (opcao.length() < 3) {
                    throw new EntradaInvalidaException("O campo não pode conter menos de 3 caracteres!");
                }

                if (perguntar.toLowerCase().contains("email") || perguntar.toLowerCase().contains("e-mail")) {
                    if (!opcao.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
                        throw new EntradaInvalidaException("Formato de e-mail inválido!");
                    }
                } else {
                    if (!permitirNumero && opcao.matches(".*\\d.*")) {
                        throw new EntradaInvalidaException("O campo não pode conter números!");
                    }
                }

                return opcao;

            } catch (EntradaInvalidaException e) {
                exibirMessagem(e.getMessage());
            }
        }
    }

    public static GeneroMusica perguntarGeneroMusical(String titulo, String perguntar) {
        GeneroMusica generoEscolhido = null;
        while (generoEscolhido == null) {
            try {
                generoEscolhido = (GeneroMusica) JOptionPane.showInputDialog(null, perguntar, titulo, JOptionPane.PLAIN_MESSAGE, null, GeneroMusica.values(), null);
                if (generoEscolhido == null) {
                    throw new EntradaInvalidaException("Você deve escolher um valor válido");
                }
            } catch (EntradaInvalidaException e) {
                exibirMessagem(e.getMessage());
            }
        }
        return generoEscolhido;
    }

    public static GeneroAudiobook perguntarGeneroAudibook(String titulo, String perguntar) {
        GeneroAudiobook generoEscolhido = null;
        while (generoEscolhido == null) {
            try {
                generoEscolhido = (GeneroAudiobook) JOptionPane.showInputDialog(null, perguntar, titulo, JOptionPane.PLAIN_MESSAGE, null, GeneroAudiobook.values(), null);
                if (generoEscolhido == null) {
                    throw new EntradaInvalidaException("Você deve escolher um valor válido");
                }
            } catch (EntradaInvalidaException e) {
                exibirMessagem(e.getMessage());
            }
        }
        return generoEscolhido;
    }
}
