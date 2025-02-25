package lection3;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertEquals;

public class SelenideTest {

    // форма регистрации заявки на обратный звонок - пример к лекции 3
    // java -jar ./artifacts/app-callback.jar -port=8081

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
