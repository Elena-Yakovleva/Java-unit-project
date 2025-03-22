package lection6.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lection6.data.DataHelper;



import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement userLogin = $("[data-test-id='login'] input");
    private SelenideElement userPassword = $("[data-test-id='password'] input");
    private SelenideElement loginPageButtom = $("[data-test-id='action-login']");

    public LoginPage() {
        userLogin.shouldBe(Condition.visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void login (DataHelper.AuthInfo info) {
        userLogin.setValue(info.getLogin());
        userPassword.setValue(info.getPassword());
        loginPageButtom.click();
    }

}
