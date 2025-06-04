package ru.yandex.praktikum.scooter.tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.scooter.pages.main.SectionImportantQuestions;

public class TestClickOnAccordionShowAccordionPanels {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    public void clickOnAccordion() {

        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        SectionImportantQuestions sectionImportantQuestions = new SectionImportantQuestions(driver);
        sectionImportantQuestions.clickEveryAccordionItem();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
