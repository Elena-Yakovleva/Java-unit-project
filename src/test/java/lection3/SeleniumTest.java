package lection3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    //java -jar ./artifacts/app-order.jar -port=7777 &

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup(); // метод настройки драйвера (для хрома) под текущее окружение
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions(); //  набор параметров для драйвера
        options.addArguments("--disable-dev-shm-usage"); // отключает использование временного хранилища
        options.addArguments("--dno-sandbox"); // отключает песочницу безопасности
        options.addArguments("--headless"); // запускает браузер Chrome в режиме без графического интерфейса
        driver = new ChromeDriver(options); //сохраняем драйвер в переменной driver
/*  driver.findElement(); - ищет элемент на странице
          .findElements(); - ищет  коллекцию элементов в виде листа
          .quit(); - закрывает драйвер
          .close(); -закрывает страницу
          .get();  - открывает страницу
          .getCurrentUrl(); - выводит текущий url
          .getPageSource(); - позволяет получить код страницы
          .manage(); - используется для доступа к функциям и свойствам драйвера
          .navigate(); - управляет историей браузера
          .switchTo(); -позволяет переключаться между различными элементами и окнами браузера    */

        driver.get("http://localhost:7777");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSendForm() {

        List<WebElement> inputElements = driver.findElements(By.cssSelector(".input__control"));// поиск коллекции элементов ввода в поля

/*         inputElements.get(0).sendKeys(); - ввод данных в строку
                        .findElement(); - поиск дочернего элемента или потомка
                        .click(); - кликнуть по элементу
                        .isDisplayed(); - возвращает статус видимости элементу - есть ли элемент на экране
                        .isSelected(); - проверить нажат ли чек-бокс
                        .clean(); - очистить поле - не всегда работает
                        .isEnabled(); - проверить включен ли элемент
                        .getText(); - получить текст, который находится внутри элемента     */
        inputElements.get(0).sendKeys("Иван Иванов");
        inputElements.get(1).sendKeys("+71112223344");

        //  или через элементы

        //driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Петров Иван"); //ввести в поле фамилию и имя
        //driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+71112223344"); //ввести номер телефона


        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).click();

        WebElement result = driver.findElement(By.cssSelector("[data-test-id='order-success']"));

        assertTrue(result.isDisplayed());
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", result.getText().trim()); //.trim - убирает пробелы до и после ввода

    }


}
