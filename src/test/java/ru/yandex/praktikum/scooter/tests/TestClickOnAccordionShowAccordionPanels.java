package ru.yandex.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.scooter.browser.Browser;
import ru.yandex.praktikum.scooter.browser.Chrome;
import ru.yandex.praktikum.scooter.pages.main.SectionImportantQuestions;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class TestClickOnAccordionShowAccordionPanels {

    private WebDriver driver;

    // готовим переменные для параметризации
    private final Browser browser;
    private final int index;
    private final String message;

    public TestClickOnAccordionShowAccordionPanels(Browser browser,
                                                   int index,
                                                   String message) {
        this.browser = browser;
        this.index = index;
        this.message = message;
    }

    // данные для тестирования
    @Parameterized.Parameters
    public static Object[][] getMessages() {
        return new Object[][] {
                {new Chrome(), 1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {new Chrome(), 2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {new Chrome(), 3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {new Chrome(), 4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {new Chrome(), 5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {new Chrome(), 6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {new Chrome(), 7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {new Chrome(), 8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void startUp() {

        browser.driverManagerSetup();
        driver = browser.getNewDriver();
        assertNotNull(driver);

    }

    @Test
    public void TestClickOnAccordion() {

        driver.get("https://qa-scooter.praktikum-services.ru");

        SectionImportantQuestions sectionImportantQuestions = new SectionImportantQuestions(driver);

        sectionImportantQuestions.clickAccordionItemNumber(index);

        assertTrue(String.format("Панель элемента номер %d не отображается", index), sectionImportantQuestions.accordionItemNumberIsDisplayed(index));
        assertEquals(String.format("Текст элемента списка номер %d отличается от требований", index),
                message, sectionImportantQuestions.getTextAccordionItemNumber(index));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
