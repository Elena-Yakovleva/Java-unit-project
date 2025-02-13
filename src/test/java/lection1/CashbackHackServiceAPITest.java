package lection1;

import org.junit.jupiter.api.Assertions;

// JUnit-api
//1. Вместо @Test прописываем @org.junit.jupiter.api.Test
//2. Вместо Assert прописывается Assertions

/* Настройки для JUnit-jupiter-api
 dependencies {
     testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.1'
     testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.6.1'
 }
 test {
    useJUnitPlatform()
 }
*/
class CashbackHackServiceAPITest {
    CashbackHackService service = new CashbackHackService();

    // Нет товаров в корзине
    @org.junit.jupiter.api.Test
    public void shouldWithoutPurchases() {

        Assertions.assertEquals(1000, service.remain(0));
    }

    // Минимальная покупка
    @org.junit.jupiter.api.Test
    public void shouldMinimumPurchase() {

        Assertions.assertEquals(999, service.remain(1));
    }

    // Покупка в пределах границ
    @org.junit.jupiter.api.Test
    public void shouldPurchaseWithinBorders() {

        Assertions.assertEquals(1, service.remain(999));
    }

    // Покупка по верхней минимальной границе для начисления кешбека
    @org.junit.jupiter.api.Test
    public void shouldPurchaseUpperLimit() {

        Assertions.assertEquals(0, service.remain(1000));
    }

    // Покупка выше верхней границы минимальной покупки для начисления кешбека
    // для нового расчета программа предлагает добавить товар.
    @org.junit.jupiter.api.Test
    public void shouldAboveUpperLimit() {

        Assertions.assertEquals(999, service.remain(1001));
    }
}