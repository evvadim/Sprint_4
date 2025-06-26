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
    private final By accordion = By.className("accordion");

    // локатор элемента списка
    private final By accordionItemHeader = By.className("accordion__button");

    // локатор панели элемента списка
    private final By accordionText = By.xpath(".//div[@class='accordion__panel']");

    public SectionImportantQuestions(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementAccordionItem(int number) {

        WebElement accordionElement = driver.findElement(accordion);
        return accordionElement.findElement(By.xpath(String.format("./*[%d]", number)));

    }



    public void clickAccordionItem(int number) {
        WebElement accordionItemNumber = findElementAccordionItem(number);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionItemNumber);
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(accordionItemNumber));
        accordionItemNumber.click();
    }

    public boolean accordionItemIsDisplayed(int number) {
        return findElementAccordionItem(number).isDisplayed();
    }

    public String getAccordionItemQuestionText(int number) {
        return findElementAccordionItem(number).findElement(accordionItemHeader).getText();
    }

    public String getAccordionItemText(int number) {
        return findElementAccordionItem(number).findElement(accordionText).getText();
    }

}
