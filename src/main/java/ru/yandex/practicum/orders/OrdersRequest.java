package ru.yandex.practicum.orders;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.courier.BaseClient;

public class OrdersRequest {
    private final String ROOT = "/orders";
    private final String GET = ROOT + "?limit="+"{limit}";

    public ValidatableResponse createWithDateFormat(OrderWithDateFormat order) {
        return BaseClient.getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();

    }

    public ValidatableResponse createWithoutDateFormat(Order order) {
        return BaseClient.getSpec()
                .body(order)
                .when()
                .post(ROOT)
                .then().log().all();

    }

    public ValidatableResponse getOrders(int limitId){
        return  BaseClient.getSpec()
                .pathParam("limit", limitId)
                .when().get(GET)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }

}
