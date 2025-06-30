package ru.yandex.praktikum.scooter.browser;

public class BrowserFactory {

    public Browser makeBrowserNamed(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome": {
                return new Chrome();
            }
            case "firefox": {
                return new FireFox();
            }
            default:
                throw new IllegalStateException("Unexpected browser name: " + browserName.toLowerCase());
        }

    }

}
