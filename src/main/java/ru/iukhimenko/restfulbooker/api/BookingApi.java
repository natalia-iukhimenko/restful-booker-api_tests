package ru.iukhimenko.restfulbooker.api;

import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import static ru.iukhimenko.restfulbooker.responsespecs.BaseResponseSpecs.success;

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
        var userCreds = new HashMap<String, String>();
        userCreds.put("username", username);
        userCreds.put("password", password);

        return given().contentType(ContentType.JSON).body(userCreds)
                .when().post(Endpoints.AUTH)
                .then().extract().body().path("token");
    }

    public static List<Integer> getAllBookingIds() {
        return given()
                .when().get(Endpoints.BOOKING)
                .then().extract().body().jsonPath().getList("bookingid");
    }
}
