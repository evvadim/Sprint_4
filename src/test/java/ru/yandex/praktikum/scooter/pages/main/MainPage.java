package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {

    private final WebDriver driver;

    // локатор кнопок «Заказать»
    private final By buttonOrder = By.xpath(String.format(".//button[text()='%s']", "Заказать"));

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickButtonOpenURL(WebElement button) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    public List<WebElement> getOrderButtons() {
        return driver.findElements(buttonOrder);
    }

}
