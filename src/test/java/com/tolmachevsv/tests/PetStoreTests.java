package com.tolmachevsv.tests;

import com.tolmachevsv.annotations.Layer;
import com.tolmachevsv.lombok.ResourceData;
import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Pet Store")
@Owner("tolmachevsv")
@Layer("rest")
public class PetStoreTests extends TestBase {

    @Test
    @AllureId("21357")
    @DisplayName("Успешное создание нового питомца")
    public void createNewPet() {
        restAssured.createNewPet(CREATE_PET);
    }

    @Test
    @AllureId("21354")
    @DisplayName("Успешное создание нового питомца с применением Lombok")
    @Tag("critical")
    public void createNewPetWithLombok() {
        ResourceData pet = restAssured.CreateNewPetWithLombok(CREATE_PET);

        step(String.format("Check if pet ID '%s' has a correct value", pet.getId()), () ->
            assertEquals(66, pet.getId()));

        step(String.format("Check if pet status is '%s'", pet.getStatus()), () ->
                assertEquals("available", pet.getStatus()));

        step(String.format("Check if pet category has value '%s'", pet.getCategory().getName()), () ->
                assertEquals("string", pet.getCategory().getName()));
    }

    @Test
    @AllureId("21353")
    @DisplayName("Создание нового питомца c невалидным запросом")
    @Tag("critical")
    public void createNewPetWithInvalidData() {
        restAssured.createNewPetWithInvalidData();
    }

    @Test
    @AllureId("21355")
    @DisplayName("Успешный запрос существующего питомца по id")
    public void getPetById() {
        restAssured.getPetById();
    }

    @Test
    @AllureId("21348")
    @DisplayName("Успешное изменение информации существующего питомца")
    public void updatePet() {
        String categoryName = restAssured.updatePet(UPDATE_PET);

        step("Check if category name has a valid value", () ->
                assertThat(categoryName).isEqualTo("update"));
    }

    @Test
    @AllureId("21351")
    @DisplayName("Успешное добавление заказа для питомца")
    @Tag("critical")
    public void addNewOrderForPet() {
        Integer orderId = restAssured.addNewOrderForPet(CREATE_ORDER);

        step(String.format("Check if order ID has a value '%s'", orderId), () ->
            assertEquals(64, orderId));
    }

    @Test
    @AllureId("21349")
    @DisplayName("Успешное удаление заказа для существующего питомца")
    @Tag("critical")
    public void deletePetOrder() {
        restAssured.deletePetOrder();
    }

    @Test
    @AllureId("21356")
    @DisplayName("Заказ не найден при удалении заказа для существующего питомца")
    @Tag("critical")
    public void deleteNotFoundOrder() {
        restAssured.deleteNotFoundOrder();
    }

    @Test
    @AllureId("21352")
    @DisplayName("Успешное создание нового пользователя")
    public void createNewUser() {
        restAssured.createNewUser(CREATE_USER);
    }

    @Test
    @AllureId("21350")
    @DisplayName("Успешный запрос на получение информации о пользователе по статусу")
    @Tag("critical")
    public void getUsersByStatus() {
        restAssured.getUsersByStatus();
    }
}