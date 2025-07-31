package util;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    public String baseURI;

    public ApiHelper(String baseUri) {
        this.baseURI = baseUri;
    }


    @Step("Send POST to /api/auth/login")
    public Response sendRequestLoginUser(String json) {
        return sendPost("/api/auth/login", json);
    }


    @Step("Send POST to /api/auth/register")
    public Response sendRequestCreateUser(String json) {
        return sendPost("/api/auth/register", json);
    }

    @Step("Send DELETE to /api/auth/user")
    public void sendRequestDeleteUser(String token) {
        given()
                .header("Authorization", token)
                .when()
                .delete(baseURI + "/api/auth/user");
    }

    private Response sendPost(String endpoint, String json) {
        return given()
                .header("Content-type", "application/json")
                .body(json)
                .when()
                .post(baseURI + endpoint);
    }
}
