import com.example.CreateOrder;
import com.example.CreateOrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreateOrderParameterizationTest {

    public CreateOrderClient createOrderClient = new CreateOrderClient();
    ArrayList<String> color;

    public CreateOrderParameterizationTest(ArrayList<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] createOrdersWithColor() {
        return new Object[][]{
                {new ArrayList<>(Collections.singleton("BLACK"))},
                {new ArrayList<>(Collections.singleton("GRAY"))},
                {new ArrayList<>(Arrays.asList("BLACK", "GRAY"))},
                {new ArrayList<String>()}
        };
    }

    @Test
    public void orderScooterSuccess() {
        CreateOrder createOrder = CreateOrder.getRandomOrder();
        createOrder.setColor(color);
        Integer orderTrack = createOrderClient.createOrder(createOrder);
        assertNotNull("is null", orderTrack);
    }
}