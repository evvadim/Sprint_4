package ru.yandex.praktikum.scooter.urls;

public class UrlAddresses {
    public static final String BASE_SCOOTER_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String ORDER_PATH = "/order";
    public static final String TRACK_PATH = "/track";
    public static final String SCOOTER_ORDER_PATH = BASE_SCOOTER_URL + ORDER_PATH;
    public static final String SCOOTER_TRACK_PATH = BASE_SCOOTER_URL + TRACK_PATH;

    public static final String DZEN_MAIN_PAGE = "https://dzen.ru/?yredirect=true";

    public static String addParameters(String name, String value) {
        return String.format("%s=%s", name, value);
    }
}
