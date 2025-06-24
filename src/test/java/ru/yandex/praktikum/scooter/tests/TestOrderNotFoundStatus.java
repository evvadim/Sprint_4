package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.scooter.browser.BrowserDriverSetup;
import ru.yandex.praktikum.scooter.pages.orderstatus.OrderStatus;

import java.util.List;

import static org.junit.Assert.*;

public class TestOrderNotFoundStatus {

    private WebDriver driver;
    private final String runUsingDriver = BrowserDriverSetup.CHROME;

    @Before
    public void startUp() {

        BrowserDriverSetup browserDriverSetup = new BrowserDriverSetup(runUsingDriver);
        browserDriverSetup.driverManagerSetup();
        driver = browserDriverSetup.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void TestOrderNotFoundIsVisible() {

        // откроем страницу с несушествующим номером заказа, например с пустым
//        driver.get("https://qa-scooter.praktikum-services.ru/track?t=60000");
//        driver.get("https://qa-scooter.praktikum-services.ru/track?t=605419");
        driver.get("https://qa-scooter.praktikum-services.ru/track?t=");

        List<WebElement> element = driver.findElements(OrderStatus.getNotFoundMessage());
        assertFalse("Объект с текстом 'Заказ не существует' не найден", element.isEmpty());
        assertTrue("Объект с текстом 'Заказ не существует' не отображается", element.get(0).isDisplayed());
    }

    @After
    public void tearDown() {
//        driver.quit();
    }

}
