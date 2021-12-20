import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {

    ScooterRegisterCourier generationCouriers = new ScooterRegisterCourier();

    public CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void creatingCourierWithRequiredFieldsCode201() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        boolean isCreated = courierClient.create(courier);
        assertTrue("Courier is not created", isCreated);

        clearData(courier);
    }

    @Test
    public void createCourierWithRequiredFieldsAndFirstNameCode201() {
        Courier courier = new CourierBuilder()
                .setRandomParams()
                .build();
        boolean isCreated = courierClient.create(courier);
        assertTrue("Courier is not created", isCreated);

        clearData(courier);
    }

    @Test
    public void creatingCourierWithoutLogin400Error() {
        Courier courier = new CourierBuilder()
                .setRandomPassword()
                .build();
        String isCreated = courierClient.createWithoutPasswordOrLogin(courier);

        assertEquals("Недостаточно данных для создания учетной записи", isCreated);
    }

    @Test
    public void creatingCourierWithoutPassword400Error() {
        Courier courier = new CourierBuilder()
                .setRandomLogin()
                .build();
        String isCreated = courierClient.createWithoutPasswordOrLogin(courier);

        assertEquals("Недостаточно данных для создания учетной записи", isCreated);
    }


    @Test
    public void createCourierExistingLoginWillReceive409Error() {
        ArrayList<String> loginPass = generationCouriers.registerNewCourierAndReturnLoginPassword();
        Courier courier = new CourierBuilder()
                .setRandomFirstName()
                .setRandomPassword()
                .setLogin(loginPass.get(0))
                .build();

        String isCreated = courierClient.createWithExistParams(courier);
        assertEquals("Этот логин уже используется. Попробуйте другой.", isCreated);
    }

    @Test
    public void creatingTwoIdenticalCouriers409Error() {
        ArrayList<String> loginPass = generationCouriers.registerNewCourierAndReturnLoginPassword();
        Courier courier = new CourierBuilder()
                .setLogin(loginPass.get(0))
                .setPassword(loginPass.get(1))
                .setFirstName(loginPass.get(2))
                .build();

        String isCreated = courierClient.createWithExistParams(courier);
        assertEquals("Этот логин уже используется. Попробуйте другой.", isCreated);
    }

    private void clearData(Courier courier) {
        int courierId = courierClient.login(CourierCredentials.getCourierCredentials(courier));
        courierClient.delete(courierId);
    }
}


