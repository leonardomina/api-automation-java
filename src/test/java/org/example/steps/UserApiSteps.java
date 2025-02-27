package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class UserApiSteps {

    private Response response;
    private static final String BASE_URL = "https://serverest.dev";
    private String usuarioId;
    private Scenario scenario;

    @Before
    public void setup(Scenario scenario) {
        this.scenario = scenario;
        System.setProperty("file.encoding", "UTF-8");
    }

    @Given("que a API de usuários está disponível")
    public void apiDisponivel() {
        response = given()
                .baseUri(BASE_URL)
                .when()
                .get("/usuarios");

        response.then().statusCode(200);
    }

    @When("eu realizo uma requisição POST para {string} com os seguintes dados:")
    public void postRequest(String endpoint, DataTable dataTable) {
        String email = "teste" + System.currentTimeMillis() + "@serverest.dev";

        List<Map<String, String>> users = dataTable.asMaps();
        Map<String, String> user = new HashMap<>(users.get(0));

        if (user.containsKey("email")) {
            user.put("email", email);
        }

        deletarUsuarioSeExistir(email);

        response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(user)
                .log().all()
                .when()
                .post(endpoint);

        response.then().statusCode(201);
        usuarioId = response.jsonPath().getString("_id");
    }

    private void deletarUsuarioSeExistir(String email) {
        Response getResponse = given()
                .baseUri(BASE_URL)
                .when()
                .get("/usuarios");

        List<Map<String, Object>> usuarios = getResponse.jsonPath().getList("usuarios");

        for (Map<String, Object> usuario : usuarios) {
            if (usuario.get("email").equals(email)) {
                String id = usuario.get("_id").toString();
                Response deleteResponse = given()
                        .baseUri(BASE_URL)
                        .when()
                        .delete("/usuarios/" + id);

                deleteResponse.then().statusCode(200);
                System.out.println("Usuário deletado com ID: " + id);
                return;
            }
        }
        System.out.println("Usuário com email " + email + " não encontrado para exclusão.");
    }

    @Then("a resposta deve conter a mensagem {string}")
    public void validarMensagem(String mensagemEsperada) {
        String mensagemRecebida = response.jsonPath().getString("message");
        System.out.println("Mensagem recebida: " + mensagemRecebida);
        assertThat(mensagemRecebida).isEqualTo(mensagemEsperada);
    }

    @Given("que um usuário com id {string} existe")
    public void usuarioExiste(String id) {
        String email = "teste" + System.currentTimeMillis() + "@serverest.dev";

        Map<String, String> usuario = new HashMap<>();
        usuario.put("nome", "Teste Atualização");
        usuario.put("email", email);
        usuario.put("password", "123456");
        usuario.put("administrador", "true");

        Response responseCriacao = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios");

        responseCriacao.then().statusCode(201);

        usuarioId = responseCriacao.jsonPath().getString("_id");
        System.out.println("Usuário criado com ID: " + usuarioId);
    }

    @When("eu realizo uma requisição PUT para {string} com os seguintes dados:")
    public void putRequest(String endpoint, DataTable dataTable) {
        // Captura os dados da tabela corretamente
        List<Map<String, String>> dadosLista = dataTable.asMaps();
        Map<String, String> dados = new HashMap<>(dadosLista.get(0));

        // Validação do ID antes de continuar
        if (usuarioId == null || usuarioId.isEmpty()) {
            throw new IllegalStateException("Erro: ID do usuário não está definido!");
        }

        // Garante que todos os campos obrigatórios estão preenchidos
        if (!dados.containsKey("email")) {
            dados.put("email", "teste" + System.currentTimeMillis() + "@serverest.dev");
        }
        if (!dados.containsKey("password")) {
            dados.put("password", "123456");
        }
        if (!dados.containsKey("administrador")) {
            dados.put("administrador", "true");
        }

        // Substitui o ID correto na URL
        String url = endpoint.replace("id_aqui", usuarioId);
        System.out.println("Atualizando usuário com ID: " + usuarioId);

        // Envia a requisição
        response = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(dados)
                .log().all()
                .when()
                .put(url);

        System.out.println("Resposta da API: " + response.body().asString());

        // Validação do status code
        response.then().statusCode(200);
    }



    @When("eu realizo uma requisição DELETE para {string}")
    public void deleteRequest(String endpoint) {
        String url = endpoint.replace("id_aqui", usuarioId);

        response = given()
                .baseUri(BASE_URL)
                .when()
                .delete(url);

        response.then().statusCode(200);
    }

    @When("eu realizo uma requisição GET para {string}")
    public void getRequest(String endpoint) {
        response = given()
                .baseUri(BASE_URL)
                .when()
                .get(endpoint);
    }

    @Then("a resposta deve conter status {int}")
    public void validarStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("a resposta deve conter uma lista de usuários")
    public void validarListaUsuarios() {
        response.then().body("usuarios", not(empty()));
    }
}
