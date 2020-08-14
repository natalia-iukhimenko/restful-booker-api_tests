package ru.iukhimenko.restfulbooker.apitests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.iukhimenko.restfulbooker.ApiTest;
import ru.iukhimenko.restfulbooker.BookingDataProvider;
import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.api.BookingApi;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iukhimenko.restfulbooker.BookingDataProvider.getBookingDTOWithAllValues;
import static ru.iukhimenko.restfulbooker.requestspecs.BookingRequestSpecs.withIdPathParam;

public class PutBookingTest extends ApiTest {
    private BookingDTO oldBooking;

    @BeforeClass
    public void makeTestBooking() {
        oldBooking = getBookingDTOWithAllValues();
        Integer id = BookingApi.postBooking(oldBooking);
        oldBooking.setId(id);
    }

    @Parameters( {"username", "password"} )
    @Test
    public void canUpdateWithValidCookie(String username, String password) {
        String tokenValue = BookingApi.getToken(username, password);
        given().spec(withIdPathParam(oldBooking.getId())).contentType(ContentType.JSON).cookie("token", tokenValue).body(getBookingDTOWithAllValues())
                .when().put(Endpoints.bookingParameterized)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Parameters( {"username", "password"} )
    @Test
    public void canUpdateWithBasicAuth(String username, String password) {
        BookingDTO updatedBooking = getBookingDTOWithAllValues();
        given().spec(withIdPathParam(oldBooking.getId())).contentType(ContentType.JSON).auth().preemptive().basic(username, password).body(updatedBooking)
                .when().put(Endpoints.bookingParameterized)
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void canNotUpdateWithoutCookieOrAuthorization() {
        BookingDTO updatedBooking = getBookingDTOWithAllValues();
        given().spec(withIdPathParam(oldBooking.getId())).contentType(ContentType.JSON).body(updatedBooking)
                .when().put(Endpoints.bookingParameterized)
                .then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Parameters({ "username", "password" })
    @Test
    public void canNotUpdateNotExisting(String username, String password) {
        BookingDTO updatedBooking = getBookingDTOWithAllValues();
        given().spec(withIdPathParam(-1)).contentType(ContentType.JSON).auth().preemptive().basic(username, password).body(updatedBooking)
                .when().put(Endpoints.bookingParameterized)
                .then().assertThat().statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }
}