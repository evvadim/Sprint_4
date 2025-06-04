package ru.yandex.praktikum.scooter.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pages.order.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestMakeNewOrder {

    // готовим переменные для параметризации
    private final String setBrowser;
    private final String firstName;
    private final String lastName;


    public TestMakeNewOrder(String setBrowser, String firstName, String lastName) {
        this.setBrowser = setBrowser;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"ff", "Василий", "Самокатов"},
                {"chr", "Самокат", "Васильев"},
        };
    }

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
    public void fillInput() {

        WebDriver driver = prepareBrower();
        Assert.assertNotNull(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/order");

        OrderPage orderPage = new OrderPage(driver);

        orderPage.makeNewOrder(firstName, lastName, "Наклонная наб., 17", "арба", "79876543210", "24.09.2024", 7, "чёрный", "привезите заряженный самокат");

        assertTrue("Неуспешное оформление заказа, текст «Заказ оформлен» не найден",orderPage.orderConfirmedTextDisplayed());

    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

}
