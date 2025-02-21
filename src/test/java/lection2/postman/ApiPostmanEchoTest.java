package lection2.postman;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiPostmanEchoTest {
    @Test
    public void shouldReturnPostRequest() {
        // Given - When - Then
// Предусловия
        given()
                .baseUri("https://postman-echo.com")
                .body("Hello, World")
// Выполняемые действия
        .when()
                .post("/post")
// Проверки
        .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .header("connection", "keep-alive")
                .body("data", equalTo("Hello, World"));
    }
}
