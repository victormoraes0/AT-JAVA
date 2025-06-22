package org.example;

import io.javalin.Javalin;
import org.example.controller.HelloController;
import org.example.controller.StatusController;
import org.example.controller.EchoController;
import org.example.controller.SaudacaoController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        HelloController.routes(app);
        StatusController.routes(app);
        EchoController.routes(app);
        SaudacaoController.routes(app);
    }
}
