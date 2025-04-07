package ru.iukhimenko.restfulbooker.requestSpecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BookingRequestSpecs {
    public static RequestSpecification withIdPathParam(Integer id) {
        return new RequestSpecBuilder().addPathParam("id", id).build();
    }
}
