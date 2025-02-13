package lection1;

import org.junit.Assert;

//  Unit4
//1. Вместо @Test прописываем @org.junit.Test
//2. Вместо Assertion указываем Assert

/* Настройки для build   только для JUnit4
   dependencies {
      testImplementation 'junit:junit:4.13'
      }
   test {
      useJUnit()
      } */

public class CashbackHackServiceJUnit4Test {
    CashbackHackService service = new CashbackHackService();

    // Нет товаров в корзине
    @org.junit.Test
    public void shouldWithoutPurchases() {

        Assert.assertEquals(1000, service.remain(0));
    }

    // Минимальная покупка
    @org.junit.Test
    public void shouldMinimumPurchase() {

        Assert.assertEquals(999, service.remain(1));
    }

    // Покупка в пределах границ
    @org.junit.Test
    public void shouldPurchaseWithinBorders() {

        Assert.assertEquals(1, service.remain(999));
    }

    // Покупка по верхней минимальной границе для начисления кешбека
    @org.junit.Test
    public void shouldPurchaseUpperLimit() {

        Assert.assertEquals(0, service.remain(1000));
    }

    // Покупка выше верхней границы минимальной покупки для начисления кешбека
    // для нового расчета программа предлагает добавить товар.
    @org.junit.Test
    public void shouldAboveUpperLimit() {

        Assert.assertEquals(999, service.remain(1001));
    }

}