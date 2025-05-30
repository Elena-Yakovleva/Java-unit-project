package lection4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MobileBankRegistrationTestV2 {

    //java -jar ./artifacts/app-ibank-visibility.jar -port=8083
    //приложением построено через сокрытие элементов

    @Test
    void shouldRegisterByAccountNumberVisibleElements() {
        Selenide.open("http://localhost:8083");  //открытие страницы
        $$(".tab-item").findBy(Condition.text("По номеру счёта")).click();
        // .findBy(Condition.visible) помогает использовать из коллекции элементов только видимые если структура страницы настроена через сокрытие элементов
        $$("[name='number'].input__control").findBy(Condition.visible).setValue("4055 0100 0123 4613 8564");
        $$("[name='phone'].input__control").findBy(Condition.visible).setValue("+7 123 123 41 25");
        $$("button").findBy(Condition.visible).click();
        //  поиск элемента по тексту и проверка его видимости при задержке видимости элемента в течение 15 сек.
        $(Selectors.withText("Успешная авторизация!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $("h2").shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds((15))).shouldBe(Condition.visible);

    }
}
