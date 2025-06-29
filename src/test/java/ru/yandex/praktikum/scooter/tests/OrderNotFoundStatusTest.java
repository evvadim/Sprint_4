package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.scooter.browser.Browser;
import ru.yandex.praktikum.scooter.browser.Chrome;
import ru.yandex.praktikum.scooter.browser.FireFox;
import ru.yandex.praktikum.scooter.pages.order.Order;
import ru.yandex.praktikum.scooter.pages.orderstatus.OrderStatus;
import ru.yandex.praktikum.scooter.urls.UrlAddresses;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderNotFoundStatusTest {

    private WebDriver driver;
    private int orderNumber;

    // готовим переменные для параметризации
    private final Browser browser;

    public OrderNotFoundStatusTest(Browser browser, int orderNumber) {
        this.browser = browser;
        this.orderNumber = Math.max(orderNumber, 0);
    }

    @Parameterized.Parameters (name = "Тестовые данные {index}: проверяем номер заказа \"{1}\"")
    public static Object[][] getStatus() {
        return new Object[][] {
//                {new FireFox(), 0}, // исключен т. к. в задании Хром, а мы значем, что в Хроме заказ не оформляется
                {new Chrome(), 15135},
                {new Chrome(), -2541},
                {new Chrome(), 0},
        };
    }

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void TestOrderNotFoundIsVisible() {

        // создадим заказ, возьмем его номер `orderNumber`, увеличим его номер на единицу
        // и удостоверимся, что такого заказа не существует
        // к сожалению работает только в firefox

        // в Хроме будем использовать случайное целое число больше 0 и менее 7 знаков если в параметрах передан 0,
        // в противном случае будем использовать переданное значение (и надеяться, что не попадем
        // в существующий номер заказа)

        if (browser instanceof FireFox) {
            driver.get(UrlAddresses.SCOOTER_ORDER_PATH);
            Order order = new Order(driver);
            order.makeNewOrder(
                    "Вадим",
                    "Петров",
                    "улица Преображенская, 31",
                    "преобр",
                    "79876543210",
                    "26.08.2025",
                    2,
                    "серая",
                    "");

            // удостоверимся, что заказ создан, получим его номер
            if (order.orderConfirmationTextDisplayed()) {
                orderNumber = order.getOrderNumber();
            }

            orderNumber += 1;

        } else if (browser instanceof Chrome) {
            if (orderNumber == 0) {
                System.out.printf("Переданный номер заказа '%d' не пренадлежит интервалу [1; 999_999], генерируем случайное число%n", orderNumber);
                orderNumber = (int) (Math.random() * (Math.pow(10, 6) - 2) + 1);
            }
        }


        String trackUrl = UrlAddresses.SCOOTER_TRACK_PATH + "?" + UrlAddresses.addParameters("t", Integer.toString(orderNumber));
        driver.get(trackUrl);

        List<WebElement> element = driver.findElements(OrderStatus.getNotFoundMessage());
        assertFalse("Объект с текстом 'Заказ не существует' не найден", element.isEmpty());
        assertTrue("Объект с текстом 'Заказ не существует' не отображается", element.get(0).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
