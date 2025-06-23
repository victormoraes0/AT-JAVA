package org.example.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final Handler cadastrarUsuario = ctx -> {
        Usuario usuario = ctx.bodyAsClass(Usuario.class);

        if (usuario.nome == null || usuario.idade <= 0 || usuario.email == null) {
            ctx.status(400).json("Dados de usuário inserido inválidos. Preencha corretamente");
            return;
        }

        usuarios.add(usuario);
        ctx.status(201).json(usuario);
    };
    private static final Handler listarUsuarios = ctx -> {
        ctx.json(usuarios);
    };
    private static final Handler buscarNome = ctx -> {
        String nomeParam = ctx.pathParam("nome");

        Usuario encontrado = usuarios.stream()
                .filter(u -> u.nome.equalsIgnoreCase(nomeParam))
                .findFirst()
                .orElse(null);

        if (encontrado == null) {
            ctx.status(404).json("Usuário não encontrado!");
        } else {
            ctx.json(encontrado);
        }
    };

    public static void routes(Javalin app) {
        app.post("/usuarios", cadastrarUsuario);
        app.get("/usuarios", listarUsuarios);
        app.get("/usuarios/{nome}", buscarNome);
    }
}