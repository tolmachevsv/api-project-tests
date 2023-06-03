package com.tolmachevsv.tests;

import com.tolmachevsv.restassured.RestAssuredSteps;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static RestAssuredSteps restAssured;
    static final String CREATE_PET = "src/test/resources/json/createPet.json";
    static final String CREATE_ORDER = "src/test/resources/json/createOrder.json";
    static final String CREATE_USER = "src/test/resources/json/createUser.json";
    static final String UPDATE_PET = "src/test/resources/json/updatePet.json";

    @BeforeAll
    public static void init() {
        restAssured = new RestAssuredSteps();
    }
}
