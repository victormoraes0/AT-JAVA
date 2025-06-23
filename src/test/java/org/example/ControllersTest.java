package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//Main precisa estar rodando para os demais testes funcionarem

public class ControllersTest {

    static final String nomeTeste = "Victor Hugo Moraes";
    static final String emailTeste = "victor@al.infnet.edu.br";
    static final int idadeTeste = 20;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 7000;
    }

    @Test
    void testeHelloStatus() {
        get("/hello")
                .then()
                .statusCode(200)
                .body(equalTo("Hello, Javalin!"));
    }

    //testCriarUsuario deve ser rodado primeiro para que demais funcionem
    @Test
    void testeCriarUsuario() {
        String requestBody = String.format("""
            {
              "nome": "%s",
              "idade": %d,
              "email": "%s"
            }
            """, nomeTeste, idadeTeste, emailTeste);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("nome", equalTo(nomeTeste))
                .body("idade", equalTo(idadeTeste))
                .body("email", equalTo(emailTeste));
    }

    @Test
    void testeBuscarUsuarioPorNome() {
        given()
                .pathParam("nome", nomeTeste)
                .when()
                .get("/usuarios/{nome}")
                .then()
                .statusCode(200)
                .body("nome", equalTo(nomeTeste))
                .body("idade", equalTo(idadeTeste))
                .body("email", equalTo(emailTeste));
    }

    @Test
    void testeListarUsuarios() {
        get("/usuarios")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("nome", hasItem(nomeTeste));
    }
}
