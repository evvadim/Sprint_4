package ru.yandex.praktikum.scooter.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverSetup {

    public static final String FIREFOX = "ff";
    public static final String CHROME = "chr";

    private final String browser;

    public BrowserDriverSetup(String browser) {
        this.browser = browser;
    }

    public void driverManagerSetup() {

        switch (browser) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                break;
        }

    }

    public WebDriver getNewDriver() {

        switch (browser) {
            case FIREFOX:
                return new FirefoxDriver();
            case CHROME:
                return new ChromeDriver();
            default:
                return null;
        }

    }

}
