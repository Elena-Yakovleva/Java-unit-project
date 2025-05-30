package lection6.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class UserСard {
        String cardNumber;
        String cardId;
    }

    public static UserСard getFirstCard(AuthInfo authInfo) {
        return new UserСard("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static UserСard getSecondCard(AuthInfo authInfo) {
        return new UserСard("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int generateValidAmount(int balance) {
        // return Math.abs(balance) / 10;
        return new Random().nextInt(balance);
    }

    public static int generateInvalidAmount(int balance) {
        // return Math.abs(balance) + 1;
        return new Random().nextInt(balance) + balance;
    }

    public static double rublesToKopecks(double balance) {
        return  balance * 100;
    }

    public static int kopecksToRubles(double balance) {
        return  (int) balance / 100;
    }





}
