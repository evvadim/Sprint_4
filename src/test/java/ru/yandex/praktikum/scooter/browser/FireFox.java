package ru.yandex.praktikum.scooter.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFox extends Browser {

    @Override
    public void driverManagerSetup() {
        WebDriverManager.firefoxdriver();
    }

    @Override
    public WebDriver getNewDriver() {
        return new FirefoxDriver();
    }
}
