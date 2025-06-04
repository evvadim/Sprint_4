package ru.yandex.praktikum.scooter.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pages.main.OrderButtons;


public class TestOrderButtonsOpenPage {

    private WebDriver driver;
    private String setBrowser = "chr";

    private WebDriver prepareBrower() {

        switch (setBrowser) {

            case "ff":
                return new FirefoxDriver();

            case "chr":
                return new ChromeDriver();

            default:
                return null;
        }

    }

    @Before
    public void startUp() {

        switch (setBrowser) {

            case "ff":
                WebDriverManager.firefoxdriver().setup();
                break;

            case "chr":
                WebDriverManager.chromedriver().setup();
                break;
        }

    }

    @Test
    public void clickOnOrderButtonHeader() {

        driver = prepareBrower();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderButtons orderButtons = new OrderButtons(driver);
        orderButtons.clickOrderButtonHeaderOpen();

    }

    @Test
    public void clickOnOrderButtonRoadmap() {

        driver = prepareBrower();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderButtons orderButtons = new OrderButtons(driver);
        orderButtons.clickOrderButtonRoadmapOpen();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
