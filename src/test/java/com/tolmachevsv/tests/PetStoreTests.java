package com.tolmachevsv.tests;

import com.tolmachevsv.lombok.ResourceData;
import com.tolmachevsv.specs.Specs;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetStoreTests extends TestBase {

    @Test
    public void createNewPet() {
        Specs.request.
                body(new File("src/test/resources/json/createPet.json")).
                when().
                post("/pet").
                then().
                log().body().
                statusCode(200).
                body("name", is("doggie")).
                body("status", is("available"));
    }

    @Test
    public void createNewPetWithLombok() {
        ResourceData resource =
                Specs.request.
                        body(new File("src/test/resources/json/createPet.json")).
                        when().
                        post("/pet").
                        then().
                        log().body().
                        statusCode(200).
                        extract().as(ResourceData.class);
        assertEquals(66, resource.getId());
        assertEquals("available", resource.getStatus());
        assertEquals("string", resource.getCategory().getName());
    }

    @Test
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

    @Test
    public void getPetById() {
        Specs.request.
                when().
                get("/pet/5").
                then().
                log().body().
                statusCode(200);
    }

    @Test
    public void updatePet() {
        String response = Specs.request.
                body(new File("src/test/resources/json/updatePet.json")).
                when().
                put("/pet").
                then().
                log().body().
                statusCode(200).extract().path("category.name");

        assertThat(response).isEqualTo("update");
    }


    @Test
    public void addNewOrderForPet() {
        Integer response = Specs.request.
                body(new File("src/test/resources/json/createOrder.json")).
                when().
                post("/store/order").
                then().
                log().body().
                extract().path("id");
        assertEquals(64, response);
    }

    @Test
    public void deletePetOrder() {
        Specs.request.
                when().
                delete("/store/order/64").
                then().
                log().body().
                statusCode(200);
    }

    @Test
    public void deleteNotFoundOrder() {
        Specs.request.
                when().
                delete("/store/order/342445").
                then().
                log().body().
                statusCode(404).
                body("message", is("Order Not Found"));
    }

    @Test
    public void createNewUser() {
        Specs.request.
                body(new File("src/test/resources/json/createUser.json")).
                when().
                post("/user").
                then().
                log().body().
                statusCode(200).
                body("message", is("666"));
    }

    @Test
    public void getUsersByStatus() {
        Specs.request.
                when().
                get("/pet/findByStatus?status=available").
                then().
                statusCode(200);
    }
}