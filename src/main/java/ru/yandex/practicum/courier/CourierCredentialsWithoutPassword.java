package ru.yandex.practicum.courier;

public class CourierCredentialsWithoutPassword {

    private final String login;

    public CourierCredentialsWithoutPassword(String login) {
        this.login = login;
    }

    public static CourierCredentialsWithoutPassword withoutPasswordFrom(Courier courier) {
        return new CourierCredentialsWithoutPassword(courier.getLogin());
    }
}
