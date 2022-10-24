import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierClient;
import ru.yandex.practicum.courier.CourierCredentials;
import ru.yandex.practicum.courier.CourierCredentialsWithoutPassword;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class CourierLoginFailedTest {
    Courier courier;
    CourierClient courierClient;

    @Before
    public void setup() {
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
        boolean isOk = courierClient.create(courier);
        assertTrue(isOk);
    }

    @Test
    @DisplayName ("Курьер не залогинился - отсутствует пароль")
    public void isCourierDoesNotLoggedInWithoutPassword() {
        CourierCredentialsWithoutPassword creds = CourierCredentialsWithoutPassword.withoutPasswordFrom(courier);
        courierClient.loginBadRequestWithoutPassword(creds)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .extract();
    }

    @Test
    @DisplayName ("Курьер не залогинился - отсутствует логин")
    public void isCourierDoesNotLoggedInWithoutLogin() {
        CourierCredentials creds = CourierCredentials.withoutLoginFrom(courier);
        courierClient.loginBadRequest(creds)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"))
                .extract();
    }

    @Test
    @DisplayName ("Курьер не залогинился - неправильный пароль")
    public void isCourierDoesNotLoggedInWithWrongPassword() {
        CourierCredentials creds = CourierCredentials.withWrongPasswordFrom(courier);
        courierClient.loginNotFound(creds)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .extract();
    }

    @Test
    @DisplayName ("Курьер не залогинился - неправильный логин")
    public void isCourierDoesNotLoggedInWithWrongLogin() {
        CourierCredentials creds = CourierCredentials.withWrongLoginFrom(courier);
        courierClient.loginNotFound(creds)
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"))
                .extract();
    }

    @After
    public void teardown() {
        CourierCredentials creds = CourierCredentials.from(courier);
        int courierId = courierClient.login(creds)
                .statusCode(200)
                .extract()
                .path("id");

        assertNotEquals(0, courierId);
        courierClient.delete(courierId);
    }
}
