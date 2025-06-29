package ru.yandex.praktikum.scooter.pages.external.dzen;

import org.openqa.selenium.By;

public class MainPage {

    // локатор логотипа на главной страницы
    private static final By dzenLogo = By.xpath(".//header[@id='dzen-header']");

    public static By getDzenLogo() {
        return dzenLogo;
    }

}
