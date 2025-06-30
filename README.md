# Sprint 4 Project
Проект тестирования учебного тренажера Яндекс Самокат.
## Технологии
- IntelliJ IDEA 2025.1.2
- JDK corretto-11.0.27
- Build System Maven
- JUnit 4.13.2
- Selenium 4.33.0

## Выбор браузера
Свойство `browser` в файле `src/test/java/ru/yandex/praktikum/scooter/config/resource.properties`

## Базовый URL
Свойство `urlBase` в файле `src/test/java/ru/yandex/praktikum/scooter/config/resource.properties`

## Тестовые сценарии
1. Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.<br>[ClickOnAccordionShowPanelsTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/ClickOnAccordionShowPanelsTest.java)
2. Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных. Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
* Нажать кнопку «Заказать». На странице две кнопки заказа.
* Заполнить форму заказа.
* Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.<br>[MakeNewOrderTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/MakeNewOrderTest.java)

## Дополнительные тестовые сценарии
1. Если нажать на логотип Самоката, попадёшь на главную страницу Самоката.<br>[ClickLogoOpenPageTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/ClickLogoOpenPageTest.java)
2. Если нажать на логотип Яндекса, в новом окне откроется главная страница Яндекса.<br>[ClickLogoOpenPageTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/ClickLogoOpenPageTest.java)
3. Ошибки для всех полей формы заказа.<br>[ErrorMessagesWhenFieldIsEmptyTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/ErrorMessagesWhenFieldIsEmptyTest.java)
4. Если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.<br>[OrderNotFoundStatusTest.java](src/test/java/ru/yandex/praktikum/scooter/tests/OrderNotFoundStatusTest.java)

## Запуск тестов
```
mvn clean test
```
