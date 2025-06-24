package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.scooter.browser.BrowserDriverSetup;
import ru.yandex.praktikum.scooter.pages.order.Order;
import ru.yandex.praktikum.scooter.pages.order.OrderFieldError;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestErrorMessagesWhenFieldIsEmpty {

    private WebDriver driver;

    // готовим переменные для паратметризации
    private final String runUsingDriver;
    private final By inputFieldLocator;
    private final By inputFieldErrorLocator;
    private final String errorMessage;

    public TestErrorMessagesWhenFieldIsEmpty(String runUsingDriver,
                                             By inputFieldLocator,
                                             By inputFieldErrorLocator,
                                             String errorMessage) {
        this.runUsingDriver = runUsingDriver;
        this.inputFieldLocator = inputFieldLocator;
        this.inputFieldErrorLocator = inputFieldErrorLocator;
        this.errorMessage = errorMessage;
    }

    // данные для тестирования
    @Parameterized.Parameters
    public static Object[][] getErrorMessages() {
        return new Object[][] {
                {BrowserDriverSetup.FIREFOX, Order.getInputFirstNameField(), OrderFieldError.getInputFirstNameFieldError(), "Введите корректное имя"},
                {BrowserDriverSetup.FIREFOX, Order.getInputLastNameField(), OrderFieldError.getInputLastNameFieldError(), "Введите корректную фамилию"},
                {BrowserDriverSetup.FIREFOX, Order.getInputAddressField(), OrderFieldError.getInputAddressFieldError(), "Введите корректный адрес"},
                {BrowserDriverSetup.FIREFOX, Order.getInputMetroField(), OrderFieldError.getInputMetroFieldError(), "Выберите станцию"},
                {BrowserDriverSetup.FIREFOX, Order.getInputPhoneNumberField(), OrderFieldError.getInputPhoneNumberFieldError() , "Введите корректный номер"},
        };
    }

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

        WebElement inputField = driver.findElement(inputFieldLocator);
        WebElement inputFieldError = inputField.findElement(inputFieldErrorLocator);
        assertTrue(String.format("При пустом поле ввода '%s' текст ошибки '%s' не отображается", inputField.getAttribute("placeholder").substring(2), errorMessage), inputFieldError.isDisplayed());
        assertEquals("Объект с текстом ошибки отображается, но текст не соответствует требованиям", errorMessage, inputFieldError.getText());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
