package com.example.clients;

import com.example.models.Order;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    private static final String PATH = "api/v1/orders/";

    @Step("Order creation")
    public ValidatableResponse create(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(PATH)
                .then();

    }

    @Step("Cancel order")
    public ValidatableResponse cancel(int track) {
        String json = "{\"track\": " + track + "}";

        return given()
                .spec(getSpec())
                .body(json)
                .when()
                .put(PATH + "cancel")
                .then();
    }

    @Step("List of orders without courier id")
    public ValidatableResponse listOfOrders() {
        return given()
                .spec(getSpec())
                .body("")
                .when()
                .get(PATH)
                .then();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }
}
