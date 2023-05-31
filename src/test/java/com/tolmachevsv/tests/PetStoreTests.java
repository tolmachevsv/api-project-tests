package com.tolmachevsv.tests;

import com.tolmachevsv.lombok.ResourceData;
import com.tolmachevsv.specs.Specs;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetStoreTests extends TestBase {

    @AllureId("21357")
    @DisplayName("Успешное создание нового питомца")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21354")
    @DisplayName("Успешное создание нового питомца с применением Lombok")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21353")
    @DisplayName("Cоздание нового питомца c невалидным запросом")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21355")
    @DisplayName("Успешный запрос существующего питомца по id")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
    @Test
    public void getPetById() {
        Specs.request.
                when().
                get("/pet/5").
                then().
                log().body().
                statusCode(200);
    }

    @AllureId("21348")
    @DisplayName("Успешное изменение информации существующего питомца")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21351")
    @DisplayName("Успешное добавление заказа для питомца")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21349")
    @DisplayName("Успешное удаление заказа для существующего питомца")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
    @Test
    public void deletePetOrder() {
        Specs.request.
                when().
                delete("/store/order/64").
                then().
                log().body().
                statusCode(200);
    }

    @AllureId("21356")
    @DisplayName("Заказ не найден при удалении заказа для существующего питомца")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21352")
    @DisplayName("Успешное создание нового пользователя")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
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

    @AllureId("21350")
    @DisplayName("Успешный запрос на получение информации о пользователе по статусу")
    @Tag("critical")
    @Feature("Pet Store")
    @Owner("tolmachevsv")
    @Test
    public void getUsersByStatus() {
        Specs.request.
                when().
                get("/pet/findByStatus?status=available").
                then().
                statusCode(200);
    }
}