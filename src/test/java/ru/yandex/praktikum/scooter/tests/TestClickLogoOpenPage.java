package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.browser.Browser;
import ru.yandex.praktikum.scooter.browser.Chrome;
import ru.yandex.praktikum.scooter.pages.main.SectionLogoInHeader;

import java.time.Duration;

import static org.junit.Assert.*;

public class TestClickLogoOpenPage {

    private WebDriver driver;
    private final Browser browser = new Chrome();

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void TestClickOnLogoScooter() {

        // вспомогательный локатор: первая секция домашней страницы
        By homePage = By.className("Home_FirstPart__3g6vG");

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

    @After
    public void tearDown() {
        driver.quit();
    }


}
