package org.example.controller;

import io.javalin.Javalin;
import java.util.Map;

public class EchoController {
    public static void routes(Javalin app) {
        app.post("/echo", ctx -> {
            Map<String, Object> body = ctx.bodyAsClass(Map.class);
            Object mensagem = body.get("mensagem");

            if (mensagem == null) {
                ctx.status(400).json(Map.of("erro", "Nenhuma mensagem encontrada."));
            } else {
                ctx.json(Map.of("mensagem", mensagem));
            }
        });
    }
}