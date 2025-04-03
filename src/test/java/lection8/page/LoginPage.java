package lection8.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import lection8.data.DataHelper;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

import static lection8.data.DataHelper.getValidAuthInfo;

public class LoginPage {

    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    private SelenideElement fieldLogin = $("[data-test-id='login'] input");
    private SelenideElement fieldPassword = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");


    public void verifyErrorNotification(String expectedText) {
        errorNotification.shouldHave(exactText(expectedText)).shouldBe(Condition.visible);
    }

    public void login (DataHelper.AuthInfo info) {
        fieldLogin.setValue(info.getLogin());
        fieldPassword.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage getValidLogin() {
        login(getValidAuthInfo());
        return new VerificationPage();
    }


}
