package org.example.client;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumoClientApi {

    private static void postNovoUsuario() {
        try {
            URL url = new URL("http://localhost:7000/usuarios");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("POST");
            conexao.setDoOutput(true);
            conexao.setRequestProperty("Content-Type", "application/json");

            String jsonInput = """
                {
                  "nome": "Victor",
                  "idade": 21,
                  "email": "victor@al.infnet.edu.br"
                }
                """;

            conexao.getOutputStream().write(jsonInput.getBytes());

            conexao.getInputStream().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarUsuarios() {
        try {
            URL url = new URL("http://localhost:7000/usuarios");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while ((linha = leitor.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void buscarUsuarioPorNome(String nome) {
        try {
            URL url = new URL("http://localhost:7000/usuarios/" + nome);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            imprimirResposta(conexao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verificarStatus() {
        try {
            URL url = new URL("http://localhost:7000/status");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            imprimirResposta(conexao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void imprimirResposta(@NotNull HttpURLConnection conexao) throws IOException {
        int codigo = conexao.getResponseCode();
        System.out.println("Resposta da requisição: " + codigo);

        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String linha;
        while ((linha = leitor.readLine()) != null) {
            System.out.println(linha);
        }
    }

    public static void main(String[] args) {
        postNovoUsuario();
        listarUsuarios();
        buscarUsuarioPorNome("Victor");
        verificarStatus();
    }
}
