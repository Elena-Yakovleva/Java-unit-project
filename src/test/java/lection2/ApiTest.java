package lection2;

import io.restassured.http.ContentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ApiTest {
    private static final Log log = LogFactory.getLog(ApiTest.class);

    //http://localhost:9999/api/v1/demo/accounts
    @Test
    public void shouldReturnDemoAcc(){
        // автотесты имеют структуру
        // Given(дано) - When(когда) - Then(затем)
        // Предусловия
        given()
                .baseUri("http://localhost:9999/api/v1") // часть адреса сервера, которая остается неизменной в тестах

        // Выполнить
                .when() //  запрос на получение информации на адрес
                .get("/demo/accounts")

        //Проверить
                .then()
                // запрос на получения статус-кода, который подтверждает что запрос обработан
                .statusCode(200)
                // запрос метаданных типа передачи информации - ожидаем, что они передаются в формате json, с кодировкой UTF-8
                .header("Content-Type", "application/json; charset=UTF-8")
                // запрос метаданных длинны информации - ожидаем длину 433
                .header("Content-Length", "433")
                // запрос на получение типа соединения - ожидаем keep-alive - т.е сервер поддерживает постоянное соединение, что ускоряет процесс обмена данными.
                .header("Connection", "keep-alive")
                // запрос на подтверждения типа передачи информации - ожидаем JSON
                .contentType(ContentType.JSON)
                // запросы на подтверждение информации в теле ответа
                // запрос на количество объектов
                .body("", hasSize(3))
                // запрос на то, что у объекта 0 в параметре id указано значение 1
                .body("[0].id", equalTo(1))
                // ... у объекта 1 в параметре name указано Текущий счёт
                .body("[1].name", equalTo("Текущий счёт"))
                // ... у объекта 2 в параметре name указано Текущий зарплатный счёт
                .body("[2].name", equalTo("Текущий зарплатный счёт"))
                // ... у объекта 1 в параметре currency указано значение USD
                .body("[1].currency", equalTo("USD"))
                // // ... у объекта 1 в параметре balance указано значение 992821429
                .body("[0].balance", equalTo(992821429));



    }

    // два варианта настройки schema для поля с несколькими вариантаминет
    //"currency": {
    //  "type": "string",
    //     "enum": ["RUB", "USD"]
    //     "pattern": "^RUB|USD"

}
