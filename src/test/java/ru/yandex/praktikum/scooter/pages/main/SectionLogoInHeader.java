package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectionLogoInHeader {

    private final WebDriver driver;

    public SectionLogoInHeader(WebDriver driver) {
        this.driver = driver;
    }

    private final By logoScooter = By.className("Header_LogoYandex__3TSOI");
    private final By logoYandex = By.className("Header_LogoScooter__3lsAR");



}
