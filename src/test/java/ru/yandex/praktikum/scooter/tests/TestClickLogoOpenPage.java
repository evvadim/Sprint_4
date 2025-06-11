package ru.yandex.praktikum.scooter.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.pages.main.SectionLogoInHeader;

import java.time.Duration;

import static org.junit.Assert.*;

public class TestClickLogoOpenPage {

    private WebDriver driver;
    private final String setBrowser = "ff";

    private WebDriver prepareBrowser() {

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

//    @Test
    public void TestClickOnLogoScooter() {

        // вспомогательный локатор: первая секция домашней страницы
        By homePage = By.className("Home_FirstPart__3g6vG");

        driver = prepareBrowser();
        assertNotNull(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/order");

        SectionLogoInHeader sectionLogoInHeader = new SectionLogoInHeader(driver);
        sectionLogoInHeader.clickLogoScooter();

        // ожидаем загрузки главной страницы
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(homePage));

        assertEquals("Главная страница сервиса не открылась", "https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());

    }

    @Test
    public void TestClickOnLogoYandex() {

        // вспомогательный локатор: логотип на открывающейся странице
        By dzenLogo = By.xpath(".//header[@id='dzen-header']");

        driver = prepareBrowser();
        assertNotNull(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/order");

        String sourceHandler = driver.getWindowHandle();

        SectionLogoInHeader sectionLogoInHeader = new SectionLogoInHeader(driver);
        sectionLogoInHeader.clickLogoYandex();

        // ищем новое окно, отличное от первоначального
        for (String handler : driver.getWindowHandles()) {
            if (!handler.equals(sourceHandler)) {
                driver.switchTo().window(handler);
                break;
            }
        }

        // ожидаем загрузки страницы (появления логотипа дзена)
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(dzenLogo));

        assertEquals("Главная страница Дзена не открылась", "https://dzen.ru/?yredirect=true", driver.getCurrentUrl());

    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }


}
