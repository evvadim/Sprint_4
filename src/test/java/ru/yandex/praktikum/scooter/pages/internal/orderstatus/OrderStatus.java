package ru.yandex.praktikum.scooter.pages.internal.orderstatus;

import org.openqa.selenium.By;

public class OrderStatus {

    // локатор объекта 'заказ не существует'
    private static final By notFoundMessage = By.xpath(".//img[@src='/assets/not-found.png']");

    // геттер локатора объекта 'заказ не существует'
    public static By getNotFoundMessage() {
        return notFoundMessage;
    }

}
