import com.example.OrderListClient;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OrderListTest {

    OrderListClient orderListClient = new OrderListClient();

    @Test
    public void checkOrderListSuccess() {
        ValidatableResponse response = orderListClient.getOrderList();

        response.assertThat().statusCode(200);
        List<Map<String, Object>> orderList = response.extract().path("orders");

        List<Integer> ids = orderList.stream().map(x -> (Integer)x.get("id")).collect(Collectors.toList());

        Assert.assertFalse(ids.isEmpty());
    }
}