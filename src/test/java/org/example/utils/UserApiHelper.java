package org.example.helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserApiHelper {
    private static final String BASE_URL = "https://serverest.dev";

    public static void deletarUsuarioSeExistir(String email) {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/usuarios");

        List<Map<String, Object>> usuarios = response.jsonPath().getList("usuarios");

        for (Map<String, Object> usuario : usuarios) {
            if (usuario.get("email").equals(email)) {
                given()
                        .baseUri(BASE_URL)
                        .when()
                        .delete("/usuarios/" + usuario.get("_id"));
                break;
            }
        }
    }

    public static Response buscarUsuarioPorId(String usuarioId) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/usuarios/" + usuarioId);
    }
}
