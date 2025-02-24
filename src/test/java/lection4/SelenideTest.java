package lection4;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class SelenideTest {

    // тест проводится для app-callback.jar

    @Test
    void shouldSendForm() {
        Selenide.open("http://localhost:8081"); // открытие страницы
        SelenideElement formElement = $("form"); // поиск элемента формы
        formElement.$("[data-test-id='name'] input").setValue("Василий"); // ввод имени и фамилии
        formElement.$("[data-test-id='phone'] input").setValue("+79993332211"); // ввод телефона
        $("[data-test-id='agreement']").click();
        $("[class='button__content']").click();
        $("[data-test-id='callback-success']")
               .should(Condition.visible, Condition.text("Ваша заявка успешно отправлена!"));
    }


}
