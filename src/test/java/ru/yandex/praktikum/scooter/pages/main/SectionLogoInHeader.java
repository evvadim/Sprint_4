package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionLogoInHeader {

    private WebDriver driver;

    public SectionLogoInHeader(WebDriver driver) {
        this.driver = driver;
    }

    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");
    private final By logoScooter = By.className("Header_LogoScooter__3lsAR");

    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }

    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }

}
