import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.orders.Order;
import ru.yandex.practicum.orders.OrdersRequest;

import static org.junit.Assert.assertNotEquals;

public class OrderRequestWithoutDateFormatTest {
    Order order;
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
    @DisplayName("Проверка создания заказа с произвольным форматом данных")
    public void isOrderCreated(){
        for(int i = 0; i < 3; i++) {
            String[] oneColor = color[i];
            order = Order.getRandomOrderWithColour(oneColor);
            ordersRequest = new OrdersRequest();
            trackId = ordersRequest.createWithoutDateFormat(order)
                    .assertThat()
                    .statusCode(201)
                    .extract()
                    .path("track");
            assertNotEquals(0, trackId);
        }
    }



}
