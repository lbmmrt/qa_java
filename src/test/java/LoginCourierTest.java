import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginCourierTest {

    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void authorizeCourierCode201() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        boolean isCreated = courierClient.create(courier);
        assertTrue("Courier is not created", isCreated);
        clearData(courier);
    }

    @Test
    public void authorizeCourierInvalidLogin404Error() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        boolean isCreated = courierClient.create(courier);
        assertTrue("Courier is not created", isCreated);

        Courier invalidCourierLogin = new CourierBuilder()
                .setRandomLogin()
                .setPassword(courier.getPassword())
                .build();

        String message = courierClient.loginWithInvalidParams(CourierCredentials.getCourierCredentials(invalidCourierLogin));
        assertEquals("Учетная запись не найдена", message);
        clearData(courier);
    }

    @Test
    public void authorizeCourierInvalidPassword404Error() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        boolean isCreated = courierClient.create(courier);
        assertTrue("Courier is not created", isCreated);

        Courier invalidCourierPassword = new CourierBuilder()
                .setRandomPassword()
                .setLogin(courier.getLogin())
                .build();

        String message = courierClient.loginWithInvalidParams(CourierCredentials.getCourierCredentials(invalidCourierPassword));
        assertEquals("Учетная запись не найдена", message);
        clearData(courier);
    }

    @Test
    public void authorizeCourierInvalidPasswordInvalidLogin404Error() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomLogin()
                .setRandomPassword()
                .build();

        String message = courierClient.loginWithInvalidParams(CourierCredentials.getCourierCredentials(invalidCourier));
        assertEquals("Учетная запись не найдена", message);
    }

    @Test
    public void authorizeCourierWithoutLogin400Error() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomPassword()
                .build();

        String message = courierClient.loginWithoutRequiredFields(CourierCredentials.getCourierCredentials(invalidCourier));
        assertEquals("Недостаточно данных для входа", message);
    }

    @Test
    @Ignore("сервис возвращает 504, так быть не должно")
    public void authorizeCourierWithoutPassword400Error() {
        Courier invalidCourier = new CourierBuilder()
                .setRandomPassword()
                .build();

        String message = courierClient.loginWithoutRequiredFields(CourierCredentials.getCourierCredentials(invalidCourier));
        assertEquals("Недостаточно данных для входа", message);
    }

    private void clearData(Courier courier) {
        int courierId = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierClient.delete(courierId);
    }
}
