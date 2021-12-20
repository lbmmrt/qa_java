package com.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateOrderClient extends RestAssuredClient {

    private final static String COURIER_PATH = "api/v1/orders";

    @Step("create order")
    public int createOrder(CreateOrder createOrder) {
        return given()
                .spec(getBaseSpec())
                .body(createOrder)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");
    }
}
