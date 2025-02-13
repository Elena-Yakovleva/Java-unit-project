package lection1;

import org.testng.Assert;

//  Особенности TestNG
//1. Вместо @Test прописывается полностью @org.testng.annotations.Test
//2. Вместо Assertion прописываем Assert
//3. actual результат прописывается до expected.

/* Настройки build.gradle для TestNG
   dependencies {
       testImplementation ('org.testng:testng:7.7.0')
}
   test {
       useTestNG()
}*/

public class CashbackHackServiceTest {
    CashbackHackService service = new CashbackHackService();

    // Нет товаров в корзине
    @org.testng.annotations.Test
    public void shouldWithoutPurchases() {

        Assert.assertEquals(service.remain(0), 1000);
    }

    // Минимальная покупка
    @org.testng.annotations.Test
    public void shouldMinimumPurchase() {

        Assert.assertEquals(service.remain(1), 999);
    }

    // Покупка в пределах границ
    @org.testng.annotations.Test
    public void shouldPurchaseWithinBorders() {

        Assert.assertEquals(service.remain(999), 1);
    }

    // Покупка по верхней минимальной границе для начисления кешбека
    @org.testng.annotations.Test
    public void shouldPurchaseUpperLimit() {

        Assert.assertEquals(service.remain(1000), 0);
    }

    // Покупка выше верхней границы минимальной покупки для начисления кешбека
    // для нового расчета программа предлагает добавить товар.
    @org.testng.annotations.Test
    public void shouldAboveUpperLimit() {

        Assert.assertEquals(service.remain(1001), 999);
    }
}