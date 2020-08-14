package ru.iukhimenko.restfulbooker.apitests;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.iukhimenko.restfulbooker.ApiTest;
import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.BookingDataProvider;
import ru.iukhimenko.restfulbooker.api.BookingApi;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iukhimenko.restfulbooker.api.BookingApi.getAllBookingIds;
import static ru.iukhimenko.restfulbooker.requestspecs.BookingRequestSpecs.withIdPathParam;

public class DeleteBookingTest extends ApiTest {
    private BookingDTO testBookingDTO;

    @BeforeMethod
    public void createTestBooking() {
        testBookingDTO = BookingDataProvider.getBookingDTOWithAllValues();
        Integer createdBookingId = BookingApi.postBooking(testBookingDTO);
        testBookingDTO.setId(createdBookingId);
    }

    @Test
    public void canNotDeleteWithoutCookieOrAuthorization() {
        given().spec(withIdPathParam(testBookingDTO.getId()))
                .when().delete(Endpoints.bookingParameterized)
                .then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Parameters( {"username", "password"} )
    @Test
    public void canDeleteWithValidCookie(String username, String password) {
        String tokenValue = BookingApi.getToken(username, password);
        given().spec(withIdPathParam(testBookingDTO.getId())).cookie("token", tokenValue)
                .when().delete(Endpoints.bookingParameterized)
                .then().statusCode(HttpStatus.SC_CREATED);

        assertThat(getAllBookingIds()).doesNotContain(testBookingDTO.getId());
    }

    @Parameters( {"username", "password"} )
    @Test
    public void canDeleteWithAuthorizationHeader(String username, String password) {
        given().spec(withIdPathParam(testBookingDTO.getId())).auth().preemptive().basic(username, password)
                .when().delete(Endpoints.bookingParameterized)
                .then().statusCode(HttpStatus.SC_CREATED);

        assertThat(getAllBookingIds()).doesNotContain(testBookingDTO.getId());
    }

    @Parameters( {"username", "password"} )
    @Test
    public void notAllowedToDeleteNotExisting(String username, String password) {
        given().spec(withIdPathParam(-1)).auth().preemptive().basic(username, password)
                .when().delete(Endpoints.bookingParameterized)
                .then().assertThat().statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
    }
}