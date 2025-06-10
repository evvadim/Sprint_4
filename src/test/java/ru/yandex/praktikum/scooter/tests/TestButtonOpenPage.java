package ru.yandex.praktikum.scooter.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pages.main.MainPageButtonsOpenPage;


public class TestButtonOpenPage {

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
    public void clickButtonOpenPage() {

        driver = prepareBrower();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPageButtonsOpenPage mainPageButtonsOpenPage = new MainPageButtonsOpenPage(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(mainPageButtonsOpenPage.buttonRoadmap));
        String result = mainPageButtonsOpenPage.clickButtonOpenURL(mainPageButtonsOpenPage.buttonRoadmap);
        System.out.println(result);

    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
