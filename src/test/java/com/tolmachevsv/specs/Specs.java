package com.tolmachevsv.specs;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.tolmachevsv.filter.CustomLogFilter.customLogFilter;
import static io.restassured.RestAssured.with;

public class Specs {

    public static RequestSpecification request = with()
            .baseUri("https://petstore.swagger.io")
            .basePath("/v2")
            .log().uri()
            .filter(customLogFilter().withCustomTemplates())
            .contentType(ContentType.JSON);
}
