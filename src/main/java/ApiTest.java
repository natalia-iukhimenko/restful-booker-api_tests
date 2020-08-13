import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;

public class ApiTest {
    @BeforeTest
    public void configure() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
