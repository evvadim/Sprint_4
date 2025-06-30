package ru.yandex.praktikum.scooter.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final String browserName;
    private static final String urlBase;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/ru/yandex/praktikum/scooter/config/resource.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        browserName = properties.getProperty("browser", "chrome");
        urlBase = properties.getProperty("urlBase","");
    }


    public static String getBrowserName() {
        return browserName;
    }
    public static String getBaseUrl() {
        return urlBase;
    }
}
