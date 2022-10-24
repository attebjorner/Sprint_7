package ru.yandex.practicum.courier;

public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public CourierCredentials(String password) {
        this.password = password;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }

    public static CourierCredentials withoutLoginFrom(Courier courier) {
        return new CourierCredentials(courier.getPassword());
    }

    public static CourierCredentials withWrongPasswordFrom(Courier courier) {
        return new CourierCredentials(courier.getLogin(), "1");
    }

    public static CourierCredentials withWrongLoginFrom(Courier courier) {
        return new CourierCredentials("1", courier.getPassword());
    }

}
