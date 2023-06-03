package com.tolmachevsv.restassured;

import com.tolmachevsv.lombok.ResourceData;
import com.tolmachevsv.specs.Specs;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;

public class RestAssuredSteps {

    public void createNewPet(final String createPet) {
        Specs.request.
                body(new File(createPet)).
                when().
                post("/pet").
                then().
                log().body().
                statusCode(200).
                body("name", is("doggie")).
                body("status", is("available"));
    }

    public ResourceData CreateNewPetWithLombok(final String createPet) {
        return
                Specs.request.
                        body(new File(createPet)).
                        when().
                        post("/pet").
                        then().
                        log().body().
                        statusCode(200).
                        extract().as(ResourceData.class);
    }

    public void createNewPetWithInvalidData() {
        Specs.request.
                body("{\"id\": \"dd\", \"category\": {\"id\": 5,\"name\": \"string\"}," +
                        "\"name\": \"doggie\",\"photoUrls\": [\"string\"],\"tags\": " +
                        "[{\"id\": 5,\"name\": \"string\"}],\"status\": \"available\"}").
                when().
                post("/pet").
                then().
                log().body().
                statusCode(500).
                body("message", is("something bad happened"));
    }

    public void getPetById() {
        Specs.request.
                when().
                get("/pet/5").
                then().
                log().body().
                statusCode(200);
    }

    public String updatePet(final String updatePet) {
        return
                Specs.request.
                        body(new File(updatePet)).
                        when().
                        put("/pet").
                        then().
                        log().body().
                        statusCode(200).
                        extract().path("category.name");
    }

    public Integer addNewOrderForPet(final String createOrder) {
        return
                Specs.request.
                        body(new File(createOrder)).
                        when().
                        post("/store/order").
                        then().
                        log().body().
                        extract().path("id");
    }

    public void deletePetOrder() {
        Specs.request.
                when().
                delete("/store/order/64").
                then().
                log().body().
                statusCode(200);
    }

    public void deleteNotFoundOrder() {
        Specs.request.
                when().
                delete("/store/order/342445").
                then().
                log().body().
                statusCode(404).
                body("message", is("Order Not Found"));
    }

    public void createNewUser(final String createUser) {
        Specs.request.
                body(new File(createUser)).
                when().
                post("/user").
                then().
                log().body().
                statusCode(200).
                body("message", is("666"));
    }

    public void getUsersByStatus() {
        Specs.request.
                when().
                get("/pet/findByStatus?status=available").
                then().
                statusCode(200);
    }
}
