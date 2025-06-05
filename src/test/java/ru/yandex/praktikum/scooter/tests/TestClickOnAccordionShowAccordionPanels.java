package ru.yandex.praktikum.scooter.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.scooter.pages.main.SectionImportantQuestions;

import static org.junit.Assert.*;

public class TestClickOnAccordionShowAccordionPanels {

    private WebDriver driver;
    private String setBrowser = "ff";


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
    public void TestClickOnAccordion() {

        driver = prepareBrower();
        assertNotNull(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        SectionImportantQuestions sectionImportantQuestions = new SectionImportantQuestions(driver);

        int number = 5;
        String text = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";

        sectionImportantQuestions.clickAccordionItemNumber(number);
        assertTrue(String.format("Панель эелемента номер %d не отображается", number), sectionImportantQuestions.accordionItemNumberIsDisplayed(number));
        assertEquals(String.format("Текст элемента списка номер %d отличается от требований", number),
                text, sectionImportantQuestions.getTextAccordionItemNumber(number));
//        System.out.printf("текст элемента %d: %s", number, sectionImportantQuestions.getTextAccordionItemNumber(number));
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

}
