package lection8.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static final Faker FAKER = new Faker(new Locale("en"));
    private DataHelper() {
    }


    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    private static String generateRandomLogin() {

        return FAKER.name().username();
    }

    private static String generateRandomPassword() {
        return FAKER.internet().password();
    }

    public static AuthInfo getRandomUser () {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuthInfo getValidAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(FAKER.numerify("######"));
    }

}
