package ru.yandex.praktikum.scooter.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

    public static String browser;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/test/java/ru/yandex/praktikum/scooter/config/resource.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        browser = properties.getProperty("browser", "chrome");
    }

}
