import com.example.Courier;
import com.example.CourierBuilder;
import com.example.CourierClient;
import com.example.CourierCredentials;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginCourierTest {

    private CourierClient courierClient;
    private Integer courierId;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void clearData() {
        if (courierId != null) {
            ValidatableResponse response = courierClient.delete(courierId);
            response.assertThat().statusCode(200);
        }
    }

    @Test
    public void authorizeCourierSuccess() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(201);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        loginResponse.assertThat()
                .statusCode(200);
        courierId = loginResponse.extract().path("id");
    }

    @Test
    public void authorizeCourierInvalidLoginWithError() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(201);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        Courier invalidCourierLogin = new CourierBuilder()
                .setRandomLogin()
                .setPassword(courier.getPassword())
                .build();

        ValidatableResponse invalidLoginResponse = courierClient.login(CourierCredentials.getCourierCredentials(invalidCourierLogin));
        invalidLoginResponse.assertThat().statusCode(404);

        String errorMessage = invalidLoginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", errorMessage);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        loginResponse.assertThat()
                .statusCode(200);
        courierId = loginResponse.extract().path("id");
    }

    @Test
    public void authorizeCourierInvalidPasswordWithError() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(201);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);

        Courier invalidCourierPassword = new CourierBuilder()
                .setRandomPassword()
                .setLogin(courier.getLogin())
                .build();

        ValidatableResponse invalidLoginResponse = courierClient.login(CourierCredentials.getCourierCredentials(invalidCourierPassword));
        invalidLoginResponse.assertThat().statusCode(404);

        String errorMessage = invalidLoginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", errorMessage);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        loginResponse.assertThat()
                .statusCode(200);
        courierId = loginResponse.extract().path("id");
    }

    @Test
    public void authorizeCourierInvalidPasswordInvalidLoginWithError() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomLogin()
                .setRandomPassword()
                .build();

        ValidatableResponse invalidLoginResponse = courierClient.login(CourierCredentials.getCourierCredentials(invalidCourier));
        invalidLoginResponse.assertThat().statusCode(404);

        String errorMessage = invalidLoginResponse.extract().path("message");
        assertEquals("Учетная запись не найдена", errorMessage);
    }

    @Test
    public void authorizeCourierWithoutLoginWithError() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomPassword()
                .build();

        ValidatableResponse invalidLoginResponse = courierClient.login(CourierCredentials.getCourierCredentials(invalidCourier));
        invalidLoginResponse.assertThat().statusCode(400);

        String errorMessage = invalidLoginResponse.extract().path("message");
        assertEquals("Недостаточно данных для входа", errorMessage);
    }

    @Test
    public void authorizeCourierWithoutPasswordWithError() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomLogin()
                .build();

        ValidatableResponse invalidLoginResponse = courierClient.login(CourierCredentials.getCourierCredentials(invalidCourier));
        invalidLoginResponse.assertThat().statusCode(400);

        String errorMessage = invalidLoginResponse.extract().path("message");
        assertEquals("Недостаточно данных для входа", errorMessage);
    }
}