import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierClient;

import static org.hamcrest.CoreMatchers.equalTo;

public class CourierRequestWithoutClientRemovalTest {


    Courier courier;
    CourierClient courierClient;

    @Before
    public void setup(){
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Курьер не создан - нет пароля")
    public void isCourierNotCreatedWithoutPassword(){
        courier = Courier.getCourierWithoutPassword();

        courierClient.createBadRequest(courier)
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .extract();

    }

    @Test
    @DisplayName("Курьер не создан - нет логина")
    public void isCourierNotCreatedWithoutLogin(){
        courier = Courier.getCourierWithoutLogin();

        courierClient.createBadRequest(courier)
                .assertThat()
                .body("message",equalTo("Недостаточно данных для создания учетной записи"))
                .extract();

    }
}
