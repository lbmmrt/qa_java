import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateOrder {

    public String firstName;
    public String lastName;
    public String address;
    public String metroStation;
    public String phone;
    public String rentTime;
    public String deliveryDate;
    public String comment;
    public ArrayList<String> color;

    public Object setColor(ArrayList<String> color) {
        this.color = color;
        return color;
    }

    public CreateOrder(String firstName, String lastName, String address, String metroStation, String phone,
                       String rentTime, String deliveryDate, String comment, ArrayList<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static CreateOrder getRandomOrder() {
        final String firstName = RandomStringUtils.randomAlphabetic(6);
        final String lastName = RandomStringUtils.randomAlphabetic(6);
        final String address = RandomStringUtils.randomAlphabetic(10);
        final String metroStation = RandomStringUtils.randomNumeric(1);
        final String phone = RandomStringUtils.randomNumeric(11);
        final String rentTime = RandomStringUtils.randomNumeric(1);
        final String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        final String comment = RandomStringUtils.randomAlphabetic(10);
        return new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, null);
    }
}
