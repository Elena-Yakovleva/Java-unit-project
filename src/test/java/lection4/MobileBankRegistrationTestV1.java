package lection4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Collection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MobileBankRegistrationTestV1 {
    // форма авторизации в личном кабинете с помощью номера карты - пример кода из лекции 4
    // java -jar ./artifacts/app-ibank-dom-modification.jar -port=8082

    @Test
    void shouldRegisterByAccountNumber() {
        Selenide.open("http://localhost:8082");  //открытие страницы
        $$(".tab-item").findBy(Condition.text("По номеру счёта")).click();
        $("[name='number'].input__control").setValue("4055 0100 0123 4613 8564");
        $("[name='phone'].input__control").setValue("+7 123 123 41 25");
        $("button").click();
        //  поиск элемента по тексту и проверка его видимости при задержке видимости элемента в течение 15 сек.
        $(Selectors.withText("Успешная авторизация!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("h2").shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds((15))).shouldBe(Condition.visible);

    }
}
