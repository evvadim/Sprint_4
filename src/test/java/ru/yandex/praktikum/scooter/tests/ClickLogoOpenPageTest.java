package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.browser.Browser;
import ru.yandex.praktikum.scooter.browser.BrowserFactory;
import ru.yandex.praktikum.scooter.config.Config;
import ru.yandex.praktikum.scooter.pages.external.dzen.MainPage;
import ru.yandex.praktikum.scooter.pages.main.SectionLogoInHeader;
import ru.yandex.praktikum.scooter.urls.UrlAddresses;

import java.time.Duration;

import static org.junit.Assert.*;

public class ClickLogoOpenPageTest {

    private WebDriver driver;
    private final Browser browser = new BrowserFactory().makeBrowserNamed(Config.browser);

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void testClickOnLogoScooter() {


        driver.get(UrlAddresses.SCOOTER_ORDER_PATH);

        SectionLogoInHeader sectionLogoInHeader = new SectionLogoInHeader(driver);
        sectionLogoInHeader.clickLogoScooter();

        // ожидаем загрузки главной страницы
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(sectionLogoInHeader.getHomePageSection()));

        assertEquals("Главная страница сервиса не открылась", UrlAddresses.BASE_SCOOTER_URL + "/", driver.getCurrentUrl());

    }

    @Test
    public void testClickOnLogoYandex() {

        driver.get(UrlAddresses.SCOOTER_ORDER_PATH);
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
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(MainPage.getDzenLogo()));

        assertEquals("Главная страница Дзена не открылась", UrlAddresses.DZEN_MAIN_PAGE, driver.getCurrentUrl());

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
