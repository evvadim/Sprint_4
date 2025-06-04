package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class OrderButtons {

    private final WebDriver driver;

    // переменная для текста кнопки
    private final String orderButtonText = "Заказать";

    /**
    * локаторы кнопок «Заказать»
    */
    private final By buttonHeader = By.xpath(String.format(".//div[@class='Header_Nav__AGCXC']/button[text()='%s']", orderButtonText));
    private final By buttonRoadmap = By.xpath(String.format(".//div[@class='Home_FinishButton__1_cWm']/button[text()='%s']", orderButtonText));


    public OrderButtons(WebDriver driver) {
        this.driver = driver;
    }

    private void clickLocatorOpen(By locator, String orderURL) {

        String startPageURL = driver.getCurrentUrl();

        WebElement button = driver.findElement(locator);

        // крутим страницу до элемента element во избежание нахождения данного элемента вне в видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);

        button.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.className("Order_Content__bmtHS")));
        assertEquals(String.format("Кнопка «%s» не ведет на страницу %s%s", orderButtonText, startPageURL, orderURL) ,startPageURL+orderURL, driver.getCurrentUrl());

    }

    public void clickOrderButtonHeaderOpen() {

        clickLocatorOpen(buttonHeader, "order");

    }

    public void clickOrderButtonRoadmapOpen() {

        clickLocatorOpen(buttonRoadmap, "order");

    }

}
