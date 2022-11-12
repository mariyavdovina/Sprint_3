package com.example.clients;

import com.example.models.Courier;
import com.example.models.Credentials;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    private static final String PATH = "api/v1/courier/";

    @Step("Courier creation")
    public ValidatableResponse create(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(PATH)
                .then();

    }

    @Step("Courier deletion")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())

                .when()
                .delete(PATH + id)
                .then();
    }

    @Step("Courier login")
    public ValidatableResponse login(Credentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH + "login/")
                .then();

    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

}
