package org.example.controllers;

import io.javalin.Javalin;
import java.time.Instant;
import java.util.Map;

public class StatusController {
    public static void routes(Javalin app) {
        app.get("/status", ctx -> {
            ctx.json(Map.of(
                    "status", "ok",
                    "timestamp", Instant.now().toString()
            ));
        });
    }
}
