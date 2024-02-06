package ru.egorovma.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.egorovma.data.UsersData;
import ru.egorovma.models.singleusers.SingleUserResponseModel;
import ru.egorovma.models.user.CreateUsersResponseModel;
import ru.egorovma.models.user.UpdateUserResponseModel;
import ru.egorovma.models.user.UserRequestModel;
import ru.egorovma.specs.Specifications;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleApiTests extends TestBase {

    UsersData user = new UsersData();
    private final static String USER = "users/2",
            POST_CREATE = "/api/users";

    @Test
    @DisplayName("Проверка email и url пользователя")
    @Description("Проверка корректного получения email и url пользователя")
    void getSingleUserTest() {
        SingleUserResponseModel response = step("GET-запрос получить пользователя", () ->
                given(Specifications.requestSpec())
                        .when()
                        .get(USER)
                        .then()
                        .spec(Specifications.responseSpec())
                        .statusCode(200)
                        .extract().as(SingleUserResponseModel.class));
        step("Проверка ответа, соответствия email", () ->
                assertThat(response.getData().getEmail()).isEqualTo(user.userEmail));
        step("Проверка ответа, соответствия url", () ->
                assertThat(response.getSupport().getUrl()).isEqualTo(user.userUrl));
    }

    @Test
    @DisplayName("Проверка создания пользователя")
    @Description("Проверка корректного создания нового пользователя")
    void postCreateTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.userJob);
        CreateUsersResponseModel response = step("POST-запрос создание пользователя", () ->
                given(Specifications.requestSpec())
                        .body(userData)
                        .when()
                        .post(POST_CREATE)
                        .then()
                        .spec(Specifications.responseSpec())
                        .statusCode(201)
                        .extract().as(CreateUsersResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertThat(response.getName()).isEqualTo(user.userName));
        step("Проверка ответа, соответствия Работы", () ->
                assertThat(response.getJob()).isEqualTo(user.userJob));
    }

    @Test
    @DisplayName("Проверка обновления клиента метолом PUT")
    @Description("Проверка работы метода PUT и корректного обновления места работы")
    void putTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.newUserJob);
        UpdateUserResponseModel response = step("PUT-запрос обновление пользователся", () ->
                given(Specifications.requestSpec())
                        .body(userData)
                        .when()
                        .put(USER)
                        .then()
                        .spec(Specifications.responseSpec())
                        .statusCode(200)
                        .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertThat(response.getName()).isEqualTo(user.userName));
        step("Проверка ответа, соответствия Работы", () ->
                assertThat(response.getJob()).isEqualTo(user.newUserJob));
    }

    @Test
    @DisplayName("Проверка обновления клиента метолом PATCH")
    @Description("Проверка работы метода PATCH и корректного обновления места работы")
    void patchUpdateTest() {
        UserRequestModel userData = new UserRequestModel(user.userName, user.newUserJob);
        UpdateUserResponseModel response = step("PATCH-запрос обновление пользователся", () ->
                given(Specifications.requestSpec())
                        .body(userData)
                        .when()
                        .patch(USER)
                        .then()
                        .spec(Specifications.responseSpec())
                        .statusCode(200)
                        .extract().as(UpdateUserResponseModel.class));
        step("Проверка ответа, соответствия Имени", () ->
                assertThat(response.getName()).isEqualTo(user.userName));
        step("Проверка ответа, соответствия Работы", () ->
                assertThat(response.getJob()).isEqualTo(user.newUserJob));
    }

    @Test
    @DisplayName("Проверка удаления пользователя")
    @Description("Проверка Статус-кода при удалении пользователя")
    void deleteDeleteTest() {
        step("DELETE-запрос удалить пользователся, проверяем Статус-код = 204", () ->
                given(Specifications.requestSpec())
                        .when()
                        .delete(USER)
                        .then())
                .spec(Specifications.responseSpec())
                .statusCode(204);
    }
}