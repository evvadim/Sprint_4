package ru.yandex.praktikum.scooter.browser;

import org.openqa.selenium.WebDriver;

public abstract class Browser {

    public abstract void driverManagerSetup();
    public abstract WebDriver getNewDriver();

}
