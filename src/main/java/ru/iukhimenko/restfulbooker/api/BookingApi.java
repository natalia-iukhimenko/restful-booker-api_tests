package ru.iukhimenko.restfulbooker.api;

import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.List;
import static io.restassured.RestAssured.given;
import static ru.iukhimenko.restfulbooker.requestspecs.BookingRequestSpecs.withIdPathParam;
import static ru.iukhimenko.restfulbooker.responsespecs.BaseResponseSpecs.success;

public class BookingApi {
    public static Integer postBooking(BookingDTO booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post(Endpoints.booking)
                .then()
                .spec(success())
                .extract().body().path("bookingid");
    }

    public static String getToken(String username, String password) {
        var userCreds = new HashMap<String, String>();
        userCreds.put("username", username);
        userCreds.put("password", password);

        String token = given().contentType(ContentType.JSON).body(userCreds)
                .when().post(Endpoints.auth)
                .then().extract().body().path("token");
        return token;
    }

    public static List<Integer> getAllBookingIds() {
        return given()
                .when().get(Endpoints.booking)
                .then().extract().body().jsonPath().getList("bookingid");
    }

    public static BookingDTO getBookingById(Integer bookingId) {
        return given().spec(withIdPathParam(bookingId))
                .when().get(Endpoints.bookingParameterized)
                .then().extract().body().as(BookingDTO.class);
    }
}
