package ru.yandex.praktikum.scooter.urls;

import ru.yandex.praktikum.scooter.config.Config;

public class UrlAddresses {
    public static final String BASE_SCOOTER_URL = Config.getBaseUrl();
    public static final String ORDER_PATH = "/order";
    public static final String TRACK_PATH = "/track";
    public static final String SCOOTER_ORDER_PATH = BASE_SCOOTER_URL + ORDER_PATH;
    public static final String SCOOTER_TRACK_PATH = BASE_SCOOTER_URL + TRACK_PATH;

    public static final String DZEN_MAIN_PAGE = "https://dzen.ru/?yredirect=true";

    public static String getTrackUrlWithParameters(String name, String value) {
        return String.format("%s?%s=%s", SCOOTER_TRACK_PATH, name, value);
    }
}
