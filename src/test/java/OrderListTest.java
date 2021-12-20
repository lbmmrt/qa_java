import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {

    OrderListClient orderListClient = new OrderListClient();

    @Test
    public void checkOrderListCode200() {
        ArrayList<String> message = orderListClient.getOrderList();
        Assert.assertNotNull(String.valueOf(message), notNullValue());
    }
}
