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
import ru.yandex.praktikum.scooter.browser.BrowserFactory;
import ru.yandex.praktikum.scooter.config.Config;
import ru.yandex.praktikum.scooter.pages.internal.order.Order;
import ru.yandex.praktikum.scooter.pages.internal.order.OrderFieldError;
import ru.yandex.praktikum.scooter.urls.UrlAddresses;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ErrorMessagesWhenFieldIsEmptyTest {

    private WebDriver driver;

    // готовим переменные для паратметризации
    private final Browser browser = new BrowserFactory().makeBrowserNamed(Config.getBrowserName());
    private final By inputFieldLocator;
    private final By inputFieldErrorLocator;
    private final String errorMessage;

    public ErrorMessagesWhenFieldIsEmptyTest(By inputFieldLocator,
                                             By inputFieldErrorLocator,
                                             String errorMessage) {
        this.inputFieldLocator = inputFieldLocator;
        this.inputFieldErrorLocator = inputFieldErrorLocator;
        this.errorMessage = errorMessage;
    }

    // данные для тестирования
    @Parameterized.Parameters (name = "Тестовые данные {index}: ожидаем текст ошибки \"{2}\"")
    public static Object[][] getErrorMessages() {
        return new Object[][] {
                {Order.getInputFirstNameField(), OrderFieldError.getInputFirstNameFieldError(), "Введите корректное имя"},
                {Order.getInputLastNameField(), OrderFieldError.getInputLastNameFieldError(), "Введите корректную фамилию"},
                {Order.getInputAddressField(), OrderFieldError.getInputAddressFieldError(), "Введите корректный адрес"},
                {Order.getInputMetroField(), OrderFieldError.getInputMetroFieldError(), "Выберите станцию"},
                {Order.getInputPhoneNumberField(), OrderFieldError.getInputPhoneNumberFieldError() , "Введите корректный номер"},
        };
    }

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void errorMessagesWhenFieldsEmptyTest() {

        driver.get(UrlAddresses.SCOOTER_ORDER_PATH);
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
