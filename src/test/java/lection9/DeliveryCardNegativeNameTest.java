package lection9;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import lection9.data.DataGenerator;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryCardNegativeNameTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public  void setup() {

        Selenide.open("http://localhost:8084");
        $("h2").shouldBe(Condition.visible, Condition.text("Карта с доставкой!"));


    }
    @Test
    public void shouldNotCreateRequestForCardWithoutName() {
        var validUser = DataGenerator.generateUser(new Locale("ru"));
        String planingDate = DataGenerator.generatePlaningDate();

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planingDate).shouldBe(Condition.visible);

        $("[data-test-id='phone'] input").shouldBe(Condition.visible).setValue(validUser.getPhoneNumber());
        $("[data-test-id='agreement']").shouldBe(Condition.visible).click();
        $(Selectors.withText("Запланировать")).shouldBe(Condition.visible).click();

        var resultMessage = $("[data-test-id='name'] .input__sub").shouldBe(Condition.text("Поле обязательно для заполнения"));

        assertTrue(resultMessage.isDisplayed());
        assertEquals("Поле обязательно для заполнения", resultMessage.getText());

    }


    @Test
    public void shouldNotCreateRequestForCardWithNotValidName() {
        var validUser = DataGenerator.generateUser(new Locale("ru"));
        String planingDate = DataGenerator.generatePlaningDate();
        String name = DataGenerator.generateName(new Locale("en"));

        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planingDate).shouldBe(Condition.visible);
        $("[data-test-id='name'] input").shouldBe(Condition.visible).setValue(name);
        $("[data-test-id='phone'] input").shouldBe(Condition.visible).setValue(validUser.getPhoneNumber());
        $("[data-test-id='agreement']").shouldBe(Condition.visible).click();
        $(Selectors.withText("Запланировать")).shouldBe(Condition.visible).click();

        var resultMessage = $("[data-test-id='name'] .input__sub").shouldBe(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

        assertTrue(resultMessage.isDisplayed());
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", resultMessage.getText());

    }
}
