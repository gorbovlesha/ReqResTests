package org.example.specifications;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {


    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification successResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification failResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectStatusCode(400)
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpecification, ResponseSpecification responseSpecification) {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
    }

    public static void installRequestSpecifications(RequestSpecification requestSpecification) {
        RestAssured.requestSpecification = requestSpecification;
    }

    public static void installResponseSpecifications(ResponseSpecification responseSpecification) {
        RestAssured.responseSpecification = responseSpecification;
    }
}
