package responsespecs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class BaseResponseSpecs {
    public static ResponseSpecification success() {
        return new ResponseSpecBuilder().expectStatusCode(200).build();
    }
}
