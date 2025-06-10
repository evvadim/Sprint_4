package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageButtonsOpenPage {

    private final WebDriver driver;

    // переменная для текста кнопки
    private final String orderButtonText = "Заказать";


    // локаторы кнопок «Заказать»
    public final By buttonHeader = By.xpath(String.format(".//div[@class='Header_Nav__AGCXC']/button[text()='%s']", orderButtonText));
    public final By buttonRoadmap = By.xpath(String.format(".//div[@class='Home_FinishButton__1_cWm']/button[text()='%s']", orderButtonText));

    // локаторы дополнительного задания
    // логотипы
//    public final By logoScooter = By.className("");


    public MainPageButtonsOpenPage(WebDriver driver) {
        this.driver = driver;
    }


    public String clickButtonOpenURL(By button) {
        driver.findElement(button).click();
        return driver.getCurrentUrl();
    }

}
