import com.example.*;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {

    private final ScooterRegisterCourier generationCouriers = new ScooterRegisterCourier();
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
    public void creatingCourierWithRequiredFieldsSuccess() {
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
    public void createCourierWithRequiredFieldsAndFirstNameSuccess() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(201);
        boolean isCreated = response.extract().path("ok");
        assertTrue("Courier is not created", isCreated);
    }

    @Test
    public void creatingCourierWithoutLoginWithError() {
        Courier courier = new CourierBuilder()
                .setRandomPassword()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(400);
        String errorMessage = response.extract().path("message");

        assertEquals("Недостаточно данных для создания учетной записи", errorMessage);
    }

    @Test
    public void creatingCourierWithoutPasswordWithError() {
        Courier courier = new CourierBuilder()
                .setRandomLogin()
                .build();
        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(400);
        String errorMessage = response.extract().path("message");

        assertEquals("Недостаточно данных для создания учетной записи", errorMessage);
    }

    @Test
    public void createCourierExistingLoginWillReceiveWithError() {
        ArrayList<String> loginPass = generationCouriers.registerNewCourierAndReturnLoginPassword();
        Courier courier = new CourierBuilder()
                .setRandomFirstName()
                .setRandomPassword()
                .setLogin(loginPass.get(0))
                .build();

        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(409);
        String errorMessage = response.extract().path("message");

        assertEquals("Этот логин уже используется. Попробуйте другой.", errorMessage);
    }

    @Test
    public void creatingTwoIdenticalCouriersWithError() {
        ArrayList<String> loginPass = generationCouriers.registerNewCourierAndReturnLoginPassword();
        Courier courier = new CourierBuilder()
                .setLogin(loginPass.get(0))
                .setPassword(loginPass.get(1))
                .setFirstName(loginPass.get(2))
                .build();

        ValidatableResponse response = courierClient.create(courier);
        response.assertThat().statusCode(409);
        String errorMessage = response.extract().path("message");

        assertEquals("Этот логин уже используется. Попробуйте другой.", errorMessage);
    }
}


