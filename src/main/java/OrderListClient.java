import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListClient extends RestAssuredClient {

    private final static String COURIER_PATH = "/api/v1/orders?courierId=";

    @Step("get order list")
    public ArrayList<String> getOrderList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(COURIER_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("orders");
    }
}
