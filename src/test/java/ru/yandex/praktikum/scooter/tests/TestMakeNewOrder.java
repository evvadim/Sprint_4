package ru.yandex.praktikum.scooter.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pages.main.MainPage;
import ru.yandex.praktikum.scooter.pages.order.Order;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestMakeNewOrder {

    private WebDriver driver;

    // готовим переменные для параметризации
    private final String setBrowser;
    private final int    orderButtonNumber;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final int    duration;
    private final String color;
    private final String comment;


    public TestMakeNewOrder(String setBrowser,
                            int    orderButtonNumber,
                            String firstName,
                            String lastName,
                            String address,
                            String metro,
                            String phone,
                            String date,
                            int    duration,
                            String color,
                            String comment) {

        // не знаю насколько правильно было выбирать браузер для тестирования через свитч
        // сделал как смог
        this.setBrowser = setBrowser;
        this.orderButtonNumber = orderButtonNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.comment = comment;
    }

    // данные для тестирования
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"chr", 0, "Василий", "Самокатов", "Газетный пер., 17", "смол", "79876556789", "21.09.2024", 2, "серая", ""},
//                {"chr", 1, "Ли", "Васильев", "Мытная ул., 31", "преобр", "79001116789", "1.09.2024", 2, "жемчуг", "очень надо"},
//                {"chr", 0, "Джон", "Петров", "Историческая площадь, 1", "тага", "79001116789", "1.03.2025", 5, "чёрный", ""},
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
    public void MakeNewOrderFullFlow() {

        driver = prepareBrower();
        assertNotNull(driver);
        driver.get("https://qa-scooter.praktikum-services.ru");
        String startURL = driver.getCurrentUrl();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonOpenURL(mainPage.getOrderButtons().get(orderButtonNumber));
        assertEquals("Адрес страницы с формой заказа отличается от требований",startURL+"order", driver.getCurrentUrl());

        Order order = new Order(driver);

        order.makeNewOrder(firstName, lastName, address, metro, phone, date, duration, color, comment);

        assertTrue("Неуспешное оформление заказа, текст «Заказ оформлен» не найден", order.orderConfirmationTextDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
