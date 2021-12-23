package com.example;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderListClient extends RestAssuredClient {

    private final static String COURIER_PATH = "/api/v1/orders";

    @Step("get order list")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(COURIER_PATH)
                .then()
                .log().all();
    }
}