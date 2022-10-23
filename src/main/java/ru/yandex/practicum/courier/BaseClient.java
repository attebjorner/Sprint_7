package ru.yandex.practicum.courier;

import io.restassured.specification.RequestSpecification;
import ru.yandex.practicum.config.Config;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public static RequestSpecification getSpec(){
        return given().log().all()
                .header("Content-Type", "application/json")
                .baseUri(Config.BASE_URL);
    }
}
