import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierClient;
import ru.yandex.practicum.courier.CourierCredentials;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;

public class CourierRequestWithClientRemovalTest {

    Courier courier;
    CourierClient courierClient;
    private int courierId;
    private String temporaryPassword;

    @Before
    public void setup(){
        courier = Courier.getRandomCourier();
        courierClient = new CourierClient();
    }

    @After
    public void teardown(){
        courier.setPassword(temporaryPassword);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .statusCode(200)
                .extract()
                .path("id");
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Курьер успешно создан")
    public void isCourierCreated(){
        boolean isOk = courierClient.create(courier);
        assertTrue(isOk);
        temporaryPassword = courier.getPassword();
    }

    @Test
    @DisplayName("Курьер успешно создан - отсутствует необязательное поле FirstName")
    public void isCourierNotCreatedWithoutFirstName(){
        courier = Courier.getCourierWithoutFirstName();
        isCourierCreated();

    }

    @Test
    @DisplayName ("Дубликат курьера не создан")
    public void isDuplicateCreated(){
        isCourierCreated();

        courierClient.createConflict(courier)
                .assertThat()
                .body("message",equalTo("Этот логин уже используется"))
                .extract();
    }

    @Test
    @DisplayName ("Курьер с существующим логином не создан")
    public void isDuplicateLoginCreated(){
        isCourierCreated();

        courier.setPassword("AnotherPassword");
        courier.setFirstName("AnotherFirstName");
        courierClient.createConflict(courier)
                .assertThat()
                .body("message",equalTo("Этот логин уже используется"))
                .extract();
    }

}
