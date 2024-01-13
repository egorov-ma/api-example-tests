package ru.egorovma;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class SimpleApiTests {

//    Разработайте 5 автотестов на запросы из https://reqres.in/
//
//    Дайте код на ревью коллегам из вашего потока.
//    После ревью они должны поставить вашему репозиторию звездочку.
//    К сдаче принимается дз с минимум 2-мя звездами.

    @Test
    void getSingleUserTest() {
        given()
                .log().uri()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void postCreateTest() {
        String userData = """
                {
                    "name": "morpheus",
                    "job": "leader"
                }""";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void putUpdateTest() {
        String userData = """
                {
                    "name": "morpheus",
                    "job": "zion resident"
                }""";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .update("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void patchUpdateTest() {
        String userData = """
                {
                    "name": "morpheus",
                    "job": "zion resident"
                }""";

        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void deleteDeleteTest() {
        given()
                .log().uri()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}