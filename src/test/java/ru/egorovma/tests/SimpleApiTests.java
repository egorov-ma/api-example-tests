package ru.egorovma.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.egorovma.data.UsersData;
import ru.egorovma.models.user.UserRequestModel;
import ru.egorovma.models.user.CreateUsersResponseModel;
import ru.egorovma.models.singleUsers.SingleUserResponseModel;
import ru.egorovma.models.user.UpdateUserResponseModel;
import ru.egorovma.specs.Specifications;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleApiTests extends TestBase {

    UsersData user = new UsersData();
    private final static String USER = "/api/users/2",
            POST_CREATE = "/api/users";

    @Test
    @Description("Проверка email и url пользователя")
    void getSingleUserTest() {
        Specifications.installSpecification(baseURI, 200);
        SingleUserResponseModel response = step("GET-запрос получить пользователся", () -> given()
                .when()
                .get(USER)
                .then()
                .extract().as(SingleUserResponseModel.class));
        step("Проверка ответа, соотвктствия email", () ->
                assertEquals(user.userEmail, response.getData().getEmail()));
        step("Проверка ответа, соотвктствия url", () ->
                assertEquals(user.userUrl, response.getSupport().getUrl()));
    }

    @Test
    @Description("Проверка создания пользователя")
    void postCreateTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.userJob);
        Specifications.installSpecification(baseURI, 201);
        CreateUsersResponseModel response = step("POST-запрос создание пользователся", () -> given()
                .body(userData)
                .when()
                .post(POST_CREATE)
                .then()
                .extract().as(CreateUsersResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertEquals(user.userName, response.getName()));
        step("Проверка ответа, соответствия Работы", () ->
                assertEquals(user.userJob, response.getJob()));
    }

    @Test
    @Description("Проверка обновления клиента метолом PUT")
    void putTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.newUserJob);
        Specifications.installSpecification(baseURI, 200);
        UpdateUserResponseModel response = step("PUT-запрос обновление пользователся", () -> given()
                .body(userData)
                .when()
                .put(USER)
                .then()
                .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertEquals(user.userName, response.getName()));
        step("Проверка ответа, соответствия Работы", () ->
                assertEquals(user.newUserJob, response.getJob()));
    }

    @Test
    @Description("Проверка обновления клиента метолом PATCH")
    void patchUpdateTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.newUserJob);
        Specifications.installSpecification(baseURI, 200);
        UpdateUserResponseModel response = step("PATCH-запрос обновление пользователся", () ->
                given()
                        .body(userData)
                        .when()
                        .patch(USER)
                        .then()
                        .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertEquals(user.userName, response.getName()));
        step("Проверка ответа, соответствия Работы", () ->
                assertEquals(user.newUserJob, response.getJob()));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    void deleteDeleteTest() {
        Specifications.installSpecification(baseURI, 204);
        step("DELETE-запрос удалить пользователся, проверяем Статус-код = 204", () -> given()
                .when()
                .delete(USER)
                .then());
    }
}