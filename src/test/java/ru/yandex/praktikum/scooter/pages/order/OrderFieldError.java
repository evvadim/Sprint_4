package ru.yandex.praktikum.scooter.pages.order;

import org.openqa.selenium.By;

public class OrderFieldError {


    // локаторы ошибок будем указывать через родителей локаторов полей ввода

    // вспомагательный локатор
    private static final By inputFieldError = By.xpath("./parent::div/div[contains(@class,'Input_ErrorMessage__3HvIb')]");

    // ошибка в вполе имя
    private static final By inputFirstNameFieldError = inputFieldError;

    // ошибка в поле фамилия
    private static final By inputLastNameFieldError = inputFieldError;

    // ошибка в поле адрес
    private static final By inputAddressFieldError = inputFieldError;

    // ошибка в поле метро
    private static final By inputMetroFieldError = By.xpath("./parent::div/parent::div/parent::div/div[@class='Order_MetroError__1BtZb']");

    //ошибка в поле номер телефона
    private static final By inputPhoneNumberFieldError = inputFieldError;

    //объекты ошибок обязательных полей второго экрана формы заказа отсутствуют, кейс скипнут
    // Как правильно отразить это в тестах?
//    private final By inputDateFieldError
//    private final By rentalDurationError


    // геттеры локаторов
    public static By getInputFirstNameFieldError() {
        return inputFirstNameFieldError;
    }

    public static By getInputLastNameFieldError() {
        return inputLastNameFieldError;
    }

    public static By getInputAddressFieldError() {
        return inputAddressFieldError;
    }

    public static By getInputMetroFieldError() {
        return inputMetroFieldError;
    }

    public static By getInputPhoneNumberFieldError() {
        return inputPhoneNumberFieldError;
    }
}
