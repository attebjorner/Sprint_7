import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierClient;
import ru.yandex.practicum.courier.CourierCredentials;

import static org.junit.Assert.*;

public class CourierLoginSuccessTest {

    Courier courier;
    CourierClient courierClient;
    private int courierId;

    @Before
    public void setup(){
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void teardown(){
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Курьер успешно залогировался")
    public void isCourierLoggedIn(){
        boolean isOk = courierClient.create(courier);
        assertTrue(isOk);

        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .statusCode(200)
                .extract()
                .path("id");

        assertNotEquals(0, courierId);
    }
}
