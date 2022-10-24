import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.yandex.practicum.orders.OrdersRequest;

import static org.hamcrest.Matchers.hasKey;

public class GetOrderTest {
    //    OrderWithDateFormat order;
    OrdersRequest ordersRequest;
    int trackId;
    String[][] color;

//    @Before
//    public void setup() {
//        color = new String[][]{
//                {"BLACK"},
//                {"GREY"},
//                {"BLACK", "GREY"}};
//    }
//
//    @Test
//    public void isOrderCreated(){
//        for(int i = 0; i < 3; i++) {
//            String[] oneColor = color[i];
//            order = OrderWithDateFormat.getRandomOrderWithParametrs(oneColor);
//            ordersRequest = new OrdersRequest();
//            trackId = ordersRequest.createWithDateFormat(order)
//                    .assertThat()
//                    .statusCode(201)
//                    .extract()
//                    .path("track");
//            assertNotEquals(0, trackId);
//        }
//    }

    @Test
    @DisplayName("Список заказов успешно получен")
    public void isGetAnyOrders() {
        ordersRequest = new OrdersRequest();
        ordersRequest.getOrders(3)
                .assertThat()
                .body("$", hasKey("orders"))
                .extract();
    }
}
