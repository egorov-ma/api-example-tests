package ru.egorovma.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
    }
}