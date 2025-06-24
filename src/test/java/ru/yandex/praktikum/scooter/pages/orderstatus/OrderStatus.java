package ru.yandex.praktikum.scooter.pages.orderstatus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatus {

    private final WebDriver driver;

    // локатор объекта 'заказ не существует'
    private static final By notFoundMessage = By.xpath(".//img[@src='/assets/not-found.png']");

    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // геттер локатора объекта 'заказ не существует'
    public static By getNotFoundMessage() {
        return notFoundMessage;
    }

}
