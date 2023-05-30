package com.tolmachevsv.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void setUp() {
        String createOrder = "src/test/resources/json/createOrder.json";
        String createPet = "src/test/resources/json/createPet.json";
        String createUser = "src/test/resources/json/createUser.json";
        String updatePet = "src/test/resources/json/updatePet.json";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverSettings.configure();
    }
}
