package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.scooter.browser.BrowserDriverSetup;
import ru.yandex.praktikum.scooter.pages.main.MainPage;
import ru.yandex.praktikum.scooter.pages.order.Order;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestMakeNewOrder {

    private WebDriver driver;

    // готовим переменные для параметризации
    private final String runUsingDriver;
    private final int    orderButtonNumber;
    private final String orderURLPath;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final int    duration;
    private final String color;
    private final String comment;


    public TestMakeNewOrder(String runUsingDriver,
                            int    orderButtonNumber, // номер кнопки «заказать»
                            String orderURLPath,
                            String firstName,
                            String lastName,
                            String address,
                            String metro,
                            String phone,
                            String date,
                            int    duration,
                            String color,
                            String comment) {

        // не знаю насколько правильно было выбирать браузер для тестирования через отдельный класс с константами
        this.runUsingDriver = runUsingDriver;
        this.orderButtonNumber = orderButtonNumber;
        this.orderURLPath = orderURLPath;
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
                {BrowserDriverSetup.CHROME, 0,"order", "Василий", "Самокатов", "Газетный пер., 17", "смол", "79876556789", "21.09.2024", 2, "серая", ""},
                {BrowserDriverSetup.CHROME, 1,"order", "Ли", "Васильев", "Мытная ул., 31", "преобр", "79001116789", "1.09.2024", 2, "жемчуг", "очень надо"},
                {BrowserDriverSetup.CHROME, 0,"order", "Джон", "Петров", "Историческая площадь, 1", "тага", "79001116789", "1.03.2025", 5, "чёрный", ""},
        };
    }

    @Before
    public void startUp() {

        BrowserDriverSetup browserDriverSetup = new BrowserDriverSetup(runUsingDriver);
        browserDriverSetup.driverManagerSetup();
        driver = browserDriverSetup.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void MakeNewOrderFullFlow() {

        driver.get("https://qa-scooter.praktikum-services.ru");
        String startURL = driver.getCurrentUrl();

        MainPage mainPage = new MainPage(driver);
        mainPage.clickButtonOpenURL(mainPage.getOrderButtons().get(orderButtonNumber));
        assertEquals("Адрес страницы с формой заказа отличается от требований",startURL+orderURLPath, driver.getCurrentUrl());

        Order order = new Order(driver);

        order.makeNewOrder(firstName, lastName, address, metro, phone, date, duration, color, comment);

        assertTrue("Неуспешное оформление заказа, текст «Заказ оформлен» не найден", order.orderConfirmationTextDisplayed());

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
