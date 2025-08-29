package br.com.streaming.plataforma.catalogo;

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


    public static void listarMidias() {
        String lista = "";
        for (Midia midia : midias) {
            lista += "\n"+midia.toString();
        }
        Utilitarios.exibirMessagem(lista);
    }





}
