package org.example.controllers;

import io.javalin.Javalin;
import java.util.Map;

public class SaudacaoController {

    public static void routes(Javalin app) {
        app.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
        });
    }
}