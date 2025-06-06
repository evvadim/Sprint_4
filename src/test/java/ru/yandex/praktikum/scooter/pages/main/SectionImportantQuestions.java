package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SectionImportantQuestions {

    private final WebDriver driver;

    // локатор списка
    private final By accordionItemsList = By.className("accordion");

    // локатор элемента списка
    private final By accordionText = By.xpath(".//div[@class='accordion__panel']");

    public SectionImportantQuestions(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementAccordionItemNumber(int number) {

        WebElement accordionItemNumber = driver.findElement(accordionItemsList);
        return accordionItemNumber.findElement(By.xpath(String.format("./*[%d]", number)));

    }

    public void clickAccordionItemNumber(int number) {
        WebElement accordionItemNumber = findElementAccordionItemNumber(number);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionItemNumber);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(accordionItemNumber));
        accordionItemNumber.click();
    }

    public boolean accordionItemNumberIsDisplayed(int number) {
        return findElementAccordionItemNumber(number).isDisplayed();
    }

    public String getTextAccordionItemNumber(int number) {
        return findElementAccordionItemNumber(number).findElement(accordionText).getText();
    }

}
