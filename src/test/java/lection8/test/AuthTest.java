package lection8.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import lection8.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static lection8.data.DataHelper.getRandomUser;
import static lection8.data.SQLHelper.*;

// java -jar ./artifacts/app-deadline.jar -port=8087 &

public class AuthTest {

    LoginPage loginPage;


    @BeforeEach
    public void setup() {
        loginPage = Selenide.open("http://localhost:8087/", LoginPage.class);
    }

    @AfterAll
    static void cleanAll() {
        cleanDataBase();
    }

    @AfterEach
    void cleanCode() {
        cleanAuthCode();
    }

    @Test
    @DisplayName("Валидная авторизация и верификация входа в ЛК")
    public void shouldGetValidVerification() {
        var verificationPage = loginPage.getValidLogin();
        var verificationCode = getCode();
        var dashBoardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("Отказ в верификации при неверном коде")
    public void shouldInvalidVerificationCode() {
        var verificationPage = loginPage.getValidLogin();
        verificationPage.randomVerify();
        verificationPage.verifyErrorNotification("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }

    @Test
    @DisplayName("Отказ в авторизации для отсутствующего пользователя")

    public void shouldInvalidUser() {
        loginPage.login(getRandomUser());
        loginPage.verifyErrorNotification("Ошибка! \nНеверно указан логин или пароль");

    }

    @Test
    @DisplayName("Превышение количества попыток ввода кода")
    public void shouldExceededNumberCodeAttempts() {
        var verificationPage = loginPage.getValidLogin();
        verificationPage.randomVerify();

        open("http://localhost:8087/");
        verificationPage = loginPage.getValidLogin();
        verificationPage.randomVerify();

        open("http://localhost:8087/");
        verificationPage = loginPage.getValidLogin();
        verificationPage.randomVerify();

        open("http://localhost:8087/");
        verificationPage = loginPage.getValidLogin();
        verificationPage.randomVerify();

        verificationPage.verifyErrorNotification("Ошибка! \nПревышено количество попыток ввода кода!");
    }


}
