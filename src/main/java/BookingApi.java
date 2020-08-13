import dto.BookingDTO;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class BookingApi {
    public static Integer postBooking(BookingDTO booking) {
        return given()
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post(Endpoints.booking)
                .then().extract().path("bookingid");
    }
}
