package ru.yandex.praktikum.scooter.pages.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderFieldError {

    private final WebDriver driver;

    // локаторы ошибок будем указывать через родителей локаторов полей ввода
    // вспомагательный локатор
    private final By inputFieldError = By.xpath("./parent::div/div[contains(@class,'Input_ErrorMessage__3HvIb')]");

    // ошибка в вполе имя
    private final By inputFirstNameFieldError = inputFieldError;

    // ошибка в поле фамилия
    private final By inputLastNameFieldError = inputFieldError;

    // ошибка в поле адрес
    private final By inputAddressFieldError = inputFieldError;

    // ошибка в поле метро
    private final By inputMetroError = By.xpath("./parent::div/div[@class='Order_MetroError__1BtZb']");

    //ошибка в поле номер телефона
    private final By inputPhoneNumberFieldError = inputFieldError;

    //объекты ошибок обязательных полей второго экрана формы заказа отсутствуют, кейс скипнут
//    private final By inputDateFieldError
//    private final By rentalDurationError


    public OrderFieldError(WebDriver driver) {
        this.driver = driver;
    }

    // геттеры локаторов
    public By getInputFirstNameFieldError() {
        return inputFirstNameFieldError;
    }

    public By getInputLastNameFieldError() {
        return inputLastNameFieldError;
    }

    public By getInputAddressFieldError() {
        return inputAddressFieldError;
    }

    public By getInputMetroError() {
        return inputMetroError;
    }

    public By getInputPhoneNumberFieldError() {
        return inputPhoneNumberFieldError;
    }
}
