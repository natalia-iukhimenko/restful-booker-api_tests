package ru.iukhimenko.restfulbooker.requestspecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BookingRequestSpecs {
    public static RequestSpecification withIdPathParam(Integer id) {
        return new RequestSpecBuilder().addPathParam("id", id).build();
    }
}
