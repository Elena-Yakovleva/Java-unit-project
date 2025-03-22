package lection6.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import lection6.data.DataHelper;
import lection6.page.DashboardPage;
import lection6.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static lection6.data.DataHelper.*;


public class DecimalMoneyTransferTest {

    DashboardPage dashboardPage;
    DataHelper.UserСard userFirstCardInfo;
    DataHelper.UserСard userSecondCardInfo;
    double firstCardBalance;
    double secondCardBalance;


    @BeforeEach
    void setupAll() {
        open("http://localhost:8086");
        var user = getAuthInfo();
        var code = getVerificationCodeFor(user);
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(user);
        dashboardPage = verificationPage.validVerify(code);
        userFirstCardInfo = DataHelper.getFirstCard(user);
        firstCardBalance = dashboardPage.getCardBalance(userFirstCardInfo);
        userSecondCardInfo = DataHelper.getSecondCard(user);
        secondCardBalance= dashboardPage.getCardBalance(userSecondCardInfo);


    }


    @Test
    void shouldTransferDecimalSumFromSecondCardToFirstCard() {

        var amount = "12,56";
        var amountDecimalSum = rublesToKopecks(Double.parseDouble(amount.replace(",", ".")));
        var expectedBalanceFirstCard = rublesToKopecks(firstCardBalance) + amountDecimalSum;
        var expectedBalanceSecondCard = rublesToKopecks(secondCardBalance) - amountDecimalSum;
        var transferPage = dashboardPage.selectCard(userFirstCardInfo);
        dashboardPage = transferPage.moneyValidTransfer(String.valueOf(amount), userSecondCardInfo);
        dashboardPage.reloadDashboardPage();

        var firstCard = kopecksToRubles(expectedBalanceFirstCard);
        var secondCard = kopecksToRubles(expectedBalanceSecondCard);
        assertAll(() -> dashboardPage.checkCardBalance(userFirstCardInfo, firstCard ),
                () -> dashboardPage.checkCardBalance( userSecondCardInfo, secondCard ));
    }


}
