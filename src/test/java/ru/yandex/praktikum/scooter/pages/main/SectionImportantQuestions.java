package ru.yandex.praktikum.scooter.pages.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SectionImportantQuestions {

    private final WebDriver driver;

    /**
     * accordionItems — локатор элементов выпадающего списка
     */
    private final By accordionItems = By.className("accordion__item");

    public SectionImportantQuestions(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEveryAccordionItem() {

        List<WebElement> accordionItemsArray = driver.findElements(accordionItems);

        assertFalse("Список «Вопросы о важном» не содержит элементов", accordionItemsArray.isEmpty());

        for (WebElement element : accordionItemsArray ) {

            // ожидаем, что в исходном состоянии элемент скрыт
            assertTrue("Панель элемента списка в исходном состоянии раскрыта", isAccordionItemHidden(element));

            clickAccordionItem(element);

            // ожидаем, что панель видна (isDisplayed) и развернута (expanded = true)
            assertTrue("Панель элемента списка не отображается", isAccordionItemDisplayed(element));
            assertTrue("Тег панели элемента списка false", isAccordionItemExpanded(element));

        }

    }

    private boolean isAccordionItemHidden(WebElement element) {

        // скроллим страницу до element во избежание нахождения данного элемента вне в видимой области
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        return !element.findElement(By.className("accordion__panel")).isDisplayed();

    }

    private void clickAccordionItem(WebElement element) {

        element.click();

        // ждём состояния "not hidden"
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(element.findElement(By.className("accordion__panel"))));

    }

    private boolean isAccordionItemDisplayed(WebElement element) {
        return element.findElement(By.className("accordion__panel")).isDisplayed();
    }

    private boolean isAccordionItemExpanded(WebElement element) {
        return Boolean.parseBoolean(element.findElement(By.xpath(".//div[@aria-expanded]")).getAttribute("aria-expanded"));
    }
}
