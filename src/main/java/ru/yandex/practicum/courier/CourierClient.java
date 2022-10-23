package ru.yandex.practicum.courier;
import io.restassured.response.ValidatableResponse;

public class CourierClient extends BaseClient {
    private final String ROOT = "/courier";
    private final String LOGIN = ROOT + "/login";
    private final String DELETE = ROOT + "/{courierId}";

    public boolean create(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .extract()
                .path("ok");

    }

    public ValidatableResponse createConflict(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(409);

    }

    public ValidatableResponse createBadRequest(Courier courier) {
        return getSpec()
                .body(courier)
                .when()
                .post(ROOT)
                .then().log().all()
                .assertThat()
                .statusCode(400);

    }

    public ValidatableResponse login(CourierCredentials creds){
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all();
    }
    public ValidatableResponse loginBadRequest(CourierCredentials creds){
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(400);
    }
    public ValidatableResponse loginBadRequestWithoutPassword(CourierCredentialsWithoutPassword creds){
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat()
                .statusCode(400);
    }
    public ValidatableResponse loginNotFound(CourierCredentials creds){
        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .statusCode(404);
    }

    public void delete(int courierId){
        getSpec()
                .pathParam("courierId", courierId)
                .when().delete(DELETE)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
