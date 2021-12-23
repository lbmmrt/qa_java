package com.example;

import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;

public class ScooterRegisterCourier {

    public ArrayList<String> registerNewCourierAndReturnLoginPassword() {
        ArrayList<String> loginPass = new ArrayList<>();
        Courier registerRequestBody = new CourierBuilder()
                .setRandomLogin()
                .setRandomPassword()
                .setRandomFirstName()
                .build();

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(registerRequestBody)
                .when()
                .post(RestAssuredClient.BASE_URL + "/api/v1/courier");

        if (response.statusCode() == 201) {
            loginPass.add(registerRequestBody.getLogin());
            loginPass.add(registerRequestBody.getPassword());
            loginPass.add(registerRequestBody.getFirstName());
        }

        return loginPass;
    }

}
