package ru.yandex.praktikum.scooter.pages.order;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OrderPage {

    private final WebDriver driver;

    // локаторы
    // поле имя
    private final By inputFirstNameField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Имя')]");

    // поле фамилия
    private final By inputLastNameField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Фамилия')]");

    // поле адрес
    private final By inputAddressField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Адрес')]");

    // элемент выбор станции метро
    private final By fieldMetro = By.className("select-search");

    // локатор для 1-го способа выбора станции
    // метод, возвращающий локатор элемента, текст которого совпадает с названием станции `station`
    private By elementStation(String station) {
        return By.xpath(String.format(".//div[contains(@class,'select-search__select')]//li[@class='select-search__row']//div[text()='%s']", station));
    }

    // локаторы для 2-го способа выбора станции
    // поле ввода названия станции
    private final By inputMetro = By.xpath(".//input[@class='select-search__input']");

    // первый элемент списка станций метро
    private final By firstElementOfList = By.xpath(".//li[1]");

    // поле телефон
    private final By inputPhoneNumberField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Телефон')]");

    // кнопка Далее на первом экране формы (переход к следующему экрану)
    private final By buttonNextStepField = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");

    // поле даты
    private final By inputDateField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Когда')]");

    // выбранная дата в календаре
    private final By selectedDate = By.className("react-datepicker__day--selected");

    // поле срок аренды
    private final By rentalDurationField = By.className("Dropdown-root");

    // элементы выпадающего списка срок аренды
    private final By rentalDurationElement = By.xpath(".//div[@class='Dropdown-menu']");

    // цвет самоката
    private final By scooterColorCheckboxGroup = By.className("Order_Checkboxes__3lWSI");

    // локатор чекбокса, содержащего `text`
    private By checkboxContainsText(String text) {
        return By.xpath(String.format(".//label[contains(text(),'%s')]/input", text));
    }

    // комментарий курьеру
    private final By inputCommentField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')][contains(@placeholder,'Комментарий')]");

    // кнопка Заказать под формой ввода
    private final By orderButtonUnderForm = By.xpath(".//div[@Class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    // копка Да подтверждения заказа
    private final By confirmOrderButton = By.xpath(".//button[contains(@class,'Button_Button__ra12g')][text()='Да']");

    // текст «Заказ оформлен» в случае удачного сценария заказа
    private final By orderConfirmationText = By.xpath(".//div[text()='Заказ оформлен']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // вспомагательный метод заполнения текстовых полей
    private void fillInputForText(By locator, String inputText) {

        WebElement input = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", input);

        input.sendKeys(inputText);

    }

    // два способа заполнить поле станция метро

    // 1-й способ: выбор станции двумя кликами
    // первым кликом раскрываем список, скроллим до нужной станции и кликаем по ней
    private void selectMetroStationByTwoClicks(String metroStation) {

        WebElement field = driver.findElement(fieldMetro);
        field.click();

        // ищем станцию в списке (в коде), скроллим список до неё и кликаем
        WebElement station = field.findElement(elementStation(metroStation));
        station.click();
    }

    // 2-й способ: частичный ввод названия и клик в списке
    // вводим несколько символов в поле, кликаем по первому пункту из отфильтрованных названий станций,
    // предполагая, что подсказка соответствует запросу
    // будем использовать его
    private void selectMetroStationByPartialInputAndClick(String metroStation) {

        WebElement field = driver.findElement(fieldMetro);

        field.findElement(inputMetro).sendKeys(metroStation);
        field.findElement(firstElementOfList).click();


    }


    public void fillFirstName(String inputText) {
        fillInputForText(inputFirstNameField, inputText);
    }

    public void fillLastName(String inputText) {
        fillInputForText(inputLastNameField, inputText);
    }

    public void fillAddress(String inputText) {
        fillInputForText(inputAddressField, inputText);
    }

    public void selectMetroStation(String station) {
//        selectMetroStationByTwoClicks(station);
        selectMetroStationByPartialInputAndClick(station);
    }

    public void fillPhoneNumber(String inputText) {
        fillInputForText(inputPhoneNumberField, inputText);
    }

    public void clickNextButton() {
        WebElement button = driver.findElement(buttonNextStepField);
        button.click();
    }

    public void fillDate(String date) {

        // вводим значение даты в виде строки
        fillInputForText(inputDateField, date);

        // для подтверждения ввода и закрития окна календаря кликаем в выбранную дату
        WebElement selectedDateCalendar = driver.findElement(selectedDate);
        selectedDateCalendar.click();

    }

    public void fillDuration(int duration) {

        // раскрываем выпадающее меню
        WebElement durationField = driver.findElement(rentalDurationField);
        durationField.click();

        // кликаем по требуемому элементу списка
        WebElement durationMenuItem = durationField.findElement(rentalDurationElement).findElement(By.xpath(String.format("./*[%d]", duration)));
        durationMenuItem.click();

    }

    public void selectScooterColor(String color) {
        WebElement checkboxGroup = driver.findElement(scooterColorCheckboxGroup);
        checkboxGroup.findElement(checkboxContainsText(color)).click();
    }

    public void addCommentForCourier(String message) {

        if (!message.isEmpty()) {
            WebElement commentField = driver.findElement(inputCommentField);
            commentField.sendKeys(message);
        }

    }

    public void clickOrderButtonUnderForm() {
        WebElement button = driver.findElement(orderButtonUnderForm);
        button.click();
    }

    public void clickConfirmOrderButton() {
        WebElement button = driver.findElement(confirmOrderButton);
        button.click();
    }

    public boolean orderConfirmationTextDisplayed() {
        List<WebElement> modal = driver.findElements(orderConfirmationText);
        return !modal.isEmpty();
    }

    public void makeNewOrder(String firstName, String lastName, String address, String metro, String phone, String date, int duration, String color, String comment) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillAddress(address);
        selectMetroStation(metro);
        fillPhoneNumber(phone);
        clickNextButton();
        fillDate(date);
        fillDuration(duration);
        selectScooterColor(color);
        addCommentForCourier(comment);
        clickOrderButtonUnderForm();
        clickConfirmOrderButton();
    }

}
