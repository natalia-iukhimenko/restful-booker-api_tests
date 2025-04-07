package ru.iukhimenko.restfulbooker.api;

import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import static ru.iukhimenko.restfulbooker.responseSpecs.BaseResponseSpecs.success;

public class BookingApi {
    public static Integer postBooking(BookingDTO booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post(Endpoints.BOOKING)
                .then()
                .spec(success())
                .extract().body().path("bookingid");
    }

    public static String getToken(String username, String password) {
        var userCredentials = new HashMap<String, String>();
        userCredentials.put("username", username);
        userCredentials.put("password", password);

        return given()
                .contentType(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post(Endpoints.AUTH)
                .then()
                .extract().body().path("token");
    }

    public static List<Integer> getAllBookingIds() {
        return given()
                .when()
                .get(Endpoints.BOOKING)
                .then().extract().body().jsonPath().getList("bookingid");
    }
}
