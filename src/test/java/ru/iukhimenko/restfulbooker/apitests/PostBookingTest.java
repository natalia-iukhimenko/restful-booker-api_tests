package ru.iukhimenko.restfulbooker.apitests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import ru.iukhimenko.restfulbooker.ApiTest;
import ru.iukhimenko.restfulbooker.BookingDataProvider;
import ru.iukhimenko.restfulbooker.Endpoints;
import ru.iukhimenko.restfulbooker.api.BookingApi;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDTO;
import ru.iukhimenko.restfulbooker.dto.booking.BookingDatesDTO;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.iukhimenko.restfulbooker.BookingDataProvider.getBookingDTOWithAllValues;
import static ru.iukhimenko.restfulbooker.api.BookingApi.getAllBookingIds;

public class PostBookingTest extends ApiTest {
    @Test(dataProvider = "withAllValues", dataProviderClass = BookingDataProvider.class)
    public void canCreateBookingWithAllValues(BookingDTO testBookingDTO) {
        Integer createdBookingId = BookingApi.postBooking(testBookingDTO);
        assertThat(getAllBookingIds()).contains(createdBookingId);
    }

    @Test(dataProvider = "withMandatoryValues", dataProviderClass = BookingDataProvider.class)
    public void canCreateBookingWithOnlyMandatoryValues(BookingDTO testBookingDTO) {
        Integer createdBookingId = BookingApi.postBooking(testBookingDTO);
        assertThat(getAllBookingIds()).contains(createdBookingId);
    }

    @Test(dataProvider = "withoutMandatoryValue", dataProviderClass = BookingDataProvider.class)
    public void canNotCreateBookingWithoutMandatoryValue(BookingDTO testBookingDTO) {
        given().contentType(ContentType.JSON).body(testBookingDTO)
                .when().post(Endpoints.booking)
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test(dataProvider = "withNegativePrice", dataProviderClass = BookingDataProvider.class)
    public void canNotCreateBookingWithNegativePrice(BookingDTO testBookingDTO) {
        testBookingDTO.setTotalPrice(-100);
        given().contentType(ContentType.JSON).body(testBookingDTO)
                .when().post(Endpoints.booking)
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test(dataProvider = "invalidDateRanges", dataProviderClass = BookingDataProvider.class)
    public void canNotCreateBookingWithInvalidDateRange(BookingDatesDTO testBookingDatesDTO) {
        BookingDTO testBookingDTO = getBookingDTOWithAllValues();
        testBookingDTO.setBookingDates(testBookingDatesDTO);
        given().contentType(ContentType.JSON).body(testBookingDTO)
                .when().post(Endpoints.booking)
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}