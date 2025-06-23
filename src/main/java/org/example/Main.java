package org.example;

import io.javalin.Javalin;
import org.example.controllers.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        HelloController.routes(app);
        StatusController.routes(app);
        EchoController.routes(app);
        SaudacaoController.routes(app);
        UsuarioController.routes(app);
    }
}
