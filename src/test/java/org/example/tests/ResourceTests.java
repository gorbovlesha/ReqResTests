package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.entity.ResourceData;
import org.example.specifications.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ResourceTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test
    public void checkIdAndYearOfResources() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.successResponseSpecification());

        List<ResourceData> resources = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceData.class);

        resources.forEach(x -> Assertions.assertEquals(1999, (x.getYear() - x.getId())));
    }

    @Test
    public void registerWithoutPassword() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.failResponseSpecification());

        Response response = given()
                .body("{\n\"email\": \"sydney@fife\"\n}")
                .when()
                .post("/api/register")
                .then().log().all()
                .extract().response();

        Assertions.assertEquals("Missing password", response.jsonPath().getString("error"));
    }

    @Test
    public void checkSingleResourceIdAndYear() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.successResponseSpecification());

        ResourceData resource = given()
                .when()
                .get("/api/unknown/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", ResourceData.class);

        Assertions.assertEquals(1999, (resource.getYear() - resource.getId()));
    }

    @Test
    public void singleResourceNotFound() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.failResponseSpecification());

        Response response = given()
                .when()
                .get("/api/unknown/23")
                .then().log().all()
                .extract().response();
    }

}
