package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.scooter.browser.Browser;
import ru.yandex.praktikum.scooter.browser.Chrome;
import ru.yandex.praktikum.scooter.pages.order.Order;
import ru.yandex.praktikum.scooter.pages.order.OrderFieldError;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestErrorMessagesWhenFieldIsEmpty {

    private WebDriver driver;

    // готовим переменные для паратметризации
    private final Browser browser;
    private final By inputFieldLocator;
    private final By inputFieldErrorLocator;
    private final String errorMessage;

    public TestErrorMessagesWhenFieldIsEmpty(Browser browser,
                                             By inputFieldLocator,
                                             By inputFieldErrorLocator,
                                             String errorMessage) {
        this.browser = browser;
        this.inputFieldLocator = inputFieldLocator;
        this.inputFieldErrorLocator = inputFieldErrorLocator;
        this.errorMessage = errorMessage;
    }

    // данные для тестирования
    @Parameterized.Parameters
    public static Object[][] getErrorMessages() {
        return new Object[][] {
                {new Chrome(), Order.getInputFirstNameField(), OrderFieldError.getInputFirstNameFieldError(), "Введите корректное имя"},
                {new Chrome(), Order.getInputLastNameField(), OrderFieldError.getInputLastNameFieldError(), "Введите корректную фамилию"},
                {new Chrome(), Order.getInputAddressField(), OrderFieldError.getInputAddressFieldError(), "Введите корректный адрес"},
                {new Chrome(), Order.getInputMetroField(), OrderFieldError.getInputMetroFieldError(), "Выберите станцию"},
                {new Chrome(), Order.getInputPhoneNumberField(), OrderFieldError.getInputPhoneNumberFieldError() , "Введите корректный номер"},
        };
    }

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void CheckErrorMessagesWhenFieldsEmpty() {

        driver.get("https://qa-scooter.praktikum-services.ru/order");
        Order order = new Order(driver);
        order.clickNextButton();

        WebElement inputField = driver.findElement(inputFieldLocator);
        WebElement inputFieldError = inputField.findElement(inputFieldErrorLocator);
        assertTrue(String.format("При пустом поле ввода '%s' текст ошибки '%s' не отображается", Objects.requireNonNull(inputField.getAttribute("placeholder")).substring(2), errorMessage), inputFieldError.isDisplayed());
        assertEquals("Объект с текстом ошибки отображается, но текст не соответствует требованиям", errorMessage, inputFieldError.getText());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
