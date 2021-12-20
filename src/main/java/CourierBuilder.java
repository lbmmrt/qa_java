import org.apache.commons.lang3.RandomStringUtils;

public class CourierBuilder {
    private String login;
    private String password;
    private String firstName;

    public CourierBuilder() {
    }

    public CourierBuilder setRandomParams() {
        this.login = RandomStringUtils.randomAlphabetic(10);
        this.password = RandomStringUtils.randomAlphabetic(10);
        this.firstName = RandomStringUtils.randomAlphabetic(10);

        return this;
    }

    public CourierBuilder setLogin(String login) {
        this.login = login;

        return this;
    }

    public CourierBuilder setRandomLogin() {
        this.login = RandomStringUtils.randomAlphabetic(10);

        return this;
    }

    public CourierBuilder setPassword(String password) {
        this.password = password;

        return this;
    }

    public CourierBuilder setRandomPassword() {
        this.password = RandomStringUtils.randomAlphabetic(10);

        return this;
    }

    public CourierBuilder setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public CourierBuilder setRandomFirstName() {
        this.firstName = RandomStringUtils.randomAlphabetic(10);

        return this;
    }

    public Courier build() {
        return new Courier(login, password, firstName);
    }
}
