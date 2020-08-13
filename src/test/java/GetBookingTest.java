import dto.BookingDTO;
import io.restassured.http.ContentType;
import static org.apache.http.HttpStatus.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static requestspecs.BookingRequestSpecs.*;
import static responsespecs.BaseResponseSpecs.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class GetBookingTest extends ApiTest {
    private BookingDTO testBookingDTO;

    @BeforeClass
    public void createTestBooking() {
        testBookingDTO = TestDataGenerator.getValidBooking();
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

    @Test
    public void getBookingByInvalidIdReturnsNotFound() {
        given()
                .spec(withIdPathParam(0))
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
