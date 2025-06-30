package ru.yandex.praktikum.scooter.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome extends Browser {

    @Override
    public void driverManagerSetup() {
        WebDriverManager.chromedriver();
    }

    @Override
    public WebDriver getNewDriver() {
        return new ChromeDriver();
    }

}
