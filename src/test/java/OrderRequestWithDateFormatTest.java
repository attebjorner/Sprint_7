import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.orders.OrderWithDateFormat;
import ru.yandex.practicum.orders.OrdersRequest;

import static org.junit.Assert.assertNotEquals;

public class OrderRequestWithDateFormatTest {
        OrderWithDateFormat order;
    OrdersRequest ordersRequest;
    int trackId;
    String[][] color;

    @Before
    public void setup() {
        color = new String[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK", "GREY"}};
    }

    @Test
    @DisplayName("Проверка создания заказа с разными цветами самокатов")
    public void isOrderCreated(){
        for(int i = 0; i < 3; i++) {
            String[] oneColor = color[i];
            order = OrderWithDateFormat.getRandomOrderWithParametrs(oneColor);
            ordersRequest = new OrdersRequest();
            trackId = ordersRequest.createWithDateFormat(order)
                    .assertThat()
                    .statusCode(201)
                    .extract()
                    .path("track");
            assertNotEquals(0, trackId);
        }
    }


}
