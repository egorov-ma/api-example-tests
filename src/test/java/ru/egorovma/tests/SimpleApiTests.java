package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.models.UserDataModel;
import ru.egorovma.models.UserResponseModel;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleApiTests extends TestBase {

//    Разработайте 5 автотестов на запросы из https://reqres.in/
//
//    Дайте код на ревью коллегам из вашего потока.
//    После ревью они должны поставить вашему репозиторию звездочку.
//    К сдаче принимается дз с минимум 2-мя звездами.

    @Test
    void getSingleUserTest() {
        UserResponseModel response = given()
                .log().uri()
                .get("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserResponseModel.class);

        assertEquals("Janet", response.getName());
        assertEquals("Weaver", response.getJob());
    }

    @Test
    void postCreateTest() {
        UserDataModel userData = new UserDataModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        UserResponseModel response = given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserResponseModel.class);

        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
    }

    @Test
    void putTest() {
        UserDataModel userData = new UserDataModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UserResponseModel response = given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .put("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserResponseModel.class);

        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());
    }

    @Test
    void patchUpdateTest() {
        UserDataModel userData = new UserDataModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        UserResponseModel response = given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .patch("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(UserResponseModel.class);

        assertEquals("morpheus", response.getName());
        assertEquals("zion resident", response.getJob());
    }

    @Test
    void deleteDeleteTest() {
        UserResponseModel response = given()
                .log().uri()
                .delete("/api/users/2")
                .then()
                .log().status()
                .statusCode(204)
                .extract().as(UserResponseModel.class);
    }
}