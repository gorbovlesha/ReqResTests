package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.entity.UserData;
import org.example.specifications.Specifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void equalIdAndAvatar() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.successResponseSpecification());

        List<UserData> users = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        users.forEach(x -> Assertions.assertTrue(x.getAvatar().contains(String.valueOf(x.getId()))));

        Assertions.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

    }
    @Test
    public void returnSingleUser() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.successResponseSpecification());

        UserData users = given()
                .when()
                .get("/api/users/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data", UserData.class);
    }

    @Test
    public void singleUserNotFound() {
        Specifications.installSpecification(Specifications.requestSpecification(), Specifications.failResponseSpecification());

        Response response = given()
                .when()
                .get("/api/users/23")
                .then().log().all()
                .extract().response();
    }
}
