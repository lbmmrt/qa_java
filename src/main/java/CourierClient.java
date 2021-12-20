import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestAssuredClient {

    private final static String COURIER_PATH = "/api/v1/courier/";

    @Step("create courier")
    public boolean create(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");
    }

    @Step("create courier with login")
    public String createWithoutPasswordOrLogin(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    @Step("create courier with login")
    public String createWithExistParams(Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(409)
                .extract()
                .path("message");
    }

    @Step("login courier")
    public int login(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("id");
    }

    @Step("login courier With Invalid Params")
    public String loginWithInvalidParams(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(404)
                .extract()
                .path("message");
    }

    @Step("login courier With Invalid Params")
    public String loginWithoutRequiredFields(CourierCredentials courierCredentials) {
        return given()
                .spec(getBaseSpec())
                .body(courierCredentials)
                .when()
                .post(COURIER_PATH + "login")
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .extract()
                .path("message");
    }

    @Step("delete courier")
    public void delete(int courierId) {
        given()
                .spec(getBaseSpec())
                .when()
                .delete(COURIER_PATH + courierId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("ok");
    }
}
