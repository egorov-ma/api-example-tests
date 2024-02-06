package ru.egorovma.specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static ru.egorovma.helpers.CustomApiListener.withCustomTemplates;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .setContentType(ContentType.JSON)
                .addFilter(withCustomTemplates())
                .build();
    }

    public static ResponseSpecification responseSpec(int code) {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .expectStatusCode(code)
                .build();
    }

    public static void installSpecification(int code) {
        RestAssured.requestSpecification = Specifications.requestSpec();
        RestAssured.responseSpecification = Specifications.responseSpec(code);
    }
}