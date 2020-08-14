package ru.iukhimenko.restfulbooker.apitests;

import org.testng.annotations.Parameters;
import ru.iukhimenko.restfulbooker.*;
import ru.iukhimenko.restfulbooker.api.BookingApi;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import io.restassured.http.ContentType;
import static org.apache.http.HttpStatus.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iukhimenko.restfulbooker.requestspecs.BookingRequestSpecs.withIdPathParam;
import static ru.iukhimenko.restfulbooker.responsespecs.BaseResponseSpecs.success;

public class GetBookingTest extends ApiTest {
    private BookingDTO testBookingDTO;

    @BeforeClass
    public void createTestBooking() {
        testBookingDTO = BookingDataProvider.getBookingDTOWithAllValues();
        Integer createdBookingId = BookingApi.postBooking(testBookingDTO);
        testBookingDTO.setId(createdBookingId);
    }

    @Test
    public void getExistingBookingByIdTest() {
        BookingDTO receivedBooking = given()
                .spec(withIdPathParam(testBookingDTO.getId()))
                .when()
                    .get(Endpoints.bookingParameterized)
                .then()
                    .spec(success())
                    .extract().body().as(BookingDTO.class);
        assertThat(receivedBooking).isEqualToIgnoringGivenFields(testBookingDTO, "id");
    }

    @Parameters({"invalidBookingId"})
    @Test
    public void getBookingByInvalidIdReturnsNotFound(Integer invalidBookingId) {
        given()
                .spec(withIdPathParam(invalidBookingId))
                .when()
                    .get(Endpoints.bookingParameterized)
                .then()
                    .assertThat().statusCode(SC_NOT_FOUND);
    }

    @Test
    public void defaultContentTypeIsJsonTest() {
        given()
                .spec(withIdPathParam(testBookingDTO.getId()))
                .when()
                    .get(Endpoints.bookingParameterized)
                .then()
                    .assertThat().contentType(ContentType.JSON).and().spec(success());
    }
}
