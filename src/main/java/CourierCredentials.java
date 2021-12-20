public class CourierCredentials {

    public final String login;
    public final String password;


    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCredentials getCourierCredentials(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }
}
