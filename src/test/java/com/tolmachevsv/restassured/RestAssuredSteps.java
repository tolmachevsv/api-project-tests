package com.tolmachevsv.restassured;

import com.tolmachevsv.lombok.ResourceData;
import com.tolmachevsv.specs.Specs;
import io.qameta.allure.Step;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;

public class RestAssuredSteps {

    @Step("POST request for creating a new pet")
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

    @Step("POST request for creating a new pet with using Lombok")
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

    @Step("POST request for creating a new pet with invalid data")
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

    @Step("GET request for getting the pet by id")
    public void getPetById() {
        Specs.request.
                when().
                get("/pet/5").
                then().
                log().body().
                statusCode(200);
    }

    @Step("PUT request for updating existing pet")
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

    @Step("POST request for creating a new order for pet")
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

    @Step("DELETE request to delete existing pet")
    public void deletePetOrder() {
        Specs.request.
                when().
                delete("/store/order/64").
                then().
                log().body().
                statusCode(200);
    }

    @Step("DELETE request to delete an non-existent pet")
    public void deleteNotFoundOrder() {
        Specs.request.
                when().
                delete("/store/order/342445").
                then().
                log().body().
                statusCode(404).
                body("message", is("Order Not Found"));
    }

    @Step("POST request to create a new user")
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

    @Step("GET request to get a user by status")
    public void getUsersByStatus() {
        Specs.request.
                when().
                get("/pet/findByStatus?status=available").
                then().
                statusCode(200);
    }
}
