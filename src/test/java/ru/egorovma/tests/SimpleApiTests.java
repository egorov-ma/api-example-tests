package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.models.createUsers.CreateUsersRequestModel;
import ru.egorovma.models.createUsers.CreateUsersResponseModel;
import ru.egorovma.models.singleUsers.SingleUserResponseModel;
import ru.egorovma.models.updateUsers.UpdateUserResponseModel;
import ru.egorovma.models.updateUsers.UpdateUsersRequestModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.egorovma.helpers.CustomApiListener.withCustomTemplates;

public class SimpleApiTests extends TestBase {

    @Test
    void getSingleUserTest() {
        SingleUserResponseModel response = step("GET-запрос получить пользователся", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().headers()
                        .log().body()
                        .get("/api/users/2")
                        .then()
                        .statusCode(200)
                        .extract().as(SingleUserResponseModel.class));
        step("Проверка ответа", () -> {
            assertEquals("janet.weaver@reqres.in", response.getData().getEmail());
            assertEquals("https://reqres.in/#support-heading", response.getSupport().getUrl());
        });
    }

    @Test
    void postCreateTest() {
        CreateUsersRequestModel userData = new CreateUsersRequestModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        CreateUsersResponseModel response = step("POST-запрос создание пользователся", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().headers()
                        .log().body()
                        .body(userData)
                        .contentType(JSON)
                        .when()
                        .post("/api/users")
                        .then()
                        .log().status()
                        .log().headers()
                        .log().body()
                        .statusCode(201)
                        .extract().as(CreateUsersResponseModel.class));
        step("Проверка ответа", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @Test
    void putTest() {
        UpdateUsersRequestModel userData = new UpdateUsersRequestModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UpdateUserResponseModel response = step("PUT-запрос обновление пользователся", () ->
                given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().headers()
                .log().body()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/api/users/2")
                .then()
                .log().status()
                .log().headers()
                .log().body()
                .statusCode(200)
                .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    void patchUpdateTest() {
        UpdateUsersRequestModel userData = new UpdateUsersRequestModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UpdateUserResponseModel response = step("PATCH-запрос обновление пользователся", () ->
                given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().headers()
                .log().body()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .patch("/api/users/2")
                .then()
                .log().status()
                .log().headers()
                .log().body()
                .statusCode(200)
                .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("zion resident", response.getJob());
        });
    }

    @Test
    void deleteDeleteTest() {
        step("DELETE-запрос удалмть пользователся", () -> given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().headers()
                .log().body()
                .delete("/api/users/2")
                .then()
                .log().status()
                .log().headers()
                .log().body()
                .statusCode(204));
    }
}