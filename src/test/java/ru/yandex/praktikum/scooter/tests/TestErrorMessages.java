package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.scooter.browser.BrowserDriverSetup;
import ru.yandex.praktikum.scooter.pages.order.Order;
import ru.yandex.praktikum.scooter.pages.order.OrderFieldError;

import static org.junit.Assert.*;

public class TestErrorMessages {

    private WebDriver driver;

    private final String runUsingDriver = BrowserDriverSetup.FIREFOX;

    @Before
    public void startUp() {

        BrowserDriverSetup browserDriverSetup = new BrowserDriverSetup(runUsingDriver);
        browserDriverSetup.driverManagerSetup();
        driver = browserDriverSetup.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void CheckErrorMessagesWhenFieldsEmpty() {

        driver.get("https://qa-scooter.praktikum-services.ru/order");
        Order order = new Order(driver);
        order.clickNextButton();

        By[] inputFieldsLocators = new By[] {
                order.getInputFirstNameField(),
                order.getInputLastNameField(),
                order.getInputAddressField(),
                order.getFieldMetro(),
                order.getInputPhoneNumberField(),
        };

        OrderFieldError orderFieldError = new OrderFieldError(driver);

        By[] inputFieldsErrorLocators = new By[] {
                orderFieldError.getInputFirstNameFieldError(),
                orderFieldError.getInputLastNameFieldError(),
                orderFieldError.getInputAddressFieldError(),
                orderFieldError.getInputMetroError(),
                orderFieldError.getInputPhoneNumberFieldError(),
        };

        String[] errorMessage = new String[] {
                "Введите корректное имя",
                "Введите корректную фамилию",
                "Введите корректный адрес",
                "Выберите станцию",
                "Введите корректный номер",
        };

        for (int i = 0; i < inputFieldsLocators.length; i++) {

            WebElement inputField = driver.findElement(inputFieldsLocators[i]);
            WebElement inputFieldError = inputField.findElement(inputFieldsErrorLocators[i]);
            assertTrue(String.format("При пустом поле ввода '%s' ошибка не отображается", inputField.getAttribute("placeholder").substring(2)), inputFieldError.isDisplayed());
            assertEquals("Текст ошибки не соответствует требованиям", errorMessage[i], inputFieldError.getText());
        }

    }

//        By[] inputFieldsLocators = new By[] {
//                order.getInputFirstNameField(),
//                order.getInputLastNameField(),
//                order.getInputAddressField(),
//                order.getFieldMetro(),
//                order.getInputPhoneNumberField()};
//
//        OrderFieldError orderFieldError = new OrderFieldError(driver);
//        orderFieldError

    @After
    public void tearDown() {
        driver.quit();
    }

}
