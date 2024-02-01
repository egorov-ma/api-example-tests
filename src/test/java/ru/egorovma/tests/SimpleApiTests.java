package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.models.singleUser.SingleUserResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleApiTests extends TestBase {

    //{
    //    "data": {
    //        "id": 2,
    //        "email": "janet.weaver@reqres.in",
    //        "first_name": "Janet",
    //        "last_name": "Weaver",
    //        "avatar": "https://reqres.in/img/faces/2-image.jpg"
    //    },
    //    "support": {
    //        "url": "https://reqres.in/#support-heading",
    //        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    //    }
    //}

    @Test
    void getSingleUserTest() {
        SingleUserResponse response = given()
                .log().uri()
                .get("/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(SingleUserResponse.class);

        assertEquals("Janet", response.getData().getFirstName());
//        assertEquals("Weaver", response.getJob());
    }

//    @Test
//    void postCreateTest() {
//        UserDataModel userData = new UserDataModel();
//        userData.setName("morpheus");
//        userData.setJob("leader");
//
//        UserResponseModel response = given()
//                .body(userData)
//                .contentType(JSON)
//                .log().uri()
//                .when()
//                .post("/api/users")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(201)
//                .extract().as(UserResponseModel.class);
//
//        assertEquals("morpheus", response.getName());
//        assertEquals("leader", response.getJob());
//    }
//
//    @Test
//    void putTest() {
//        UserDataModel userData = new UserDataModel();
//        userData.setName("morpheus");
//        userData.setJob("zion resident");
//
//        UserResponseModel response = given()
//                .body(userData)
//                .contentType(JSON)
//                .log().uri()
//                .when()
//                .put("/api/users/2")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .extract().as(UserResponseModel.class);
//
//        assertEquals("morpheus", response.getName());
//        assertEquals("zion resident", response.getJob());
//    }
//
//    @Test
//    void patchUpdateTest() {
//        UserDataModel userData = new UserDataModel();
//        userData.setName("morpheus");
//        userData.setJob("zion resident");
//
//        UserResponseModel response = given()
//                .body(userData)
//                .contentType(JSON)
//                .log().uri()
//                .when()
//                .patch("/api/users/2")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .extract().as(UserResponseModel.class);
//
//        assertEquals("morpheus", response.getName());
//        assertEquals("zion resident", response.getJob());
//    }
//
//    @Test
//    void deleteDeleteTest() {
//        UserResponseModel response = given()
//                .log().uri()
//                .delete("/api/users/2")
//                .then()
//                .log().status()
//                .statusCode(204)
//                .extract().as(UserResponseModel.class);
//    }
}