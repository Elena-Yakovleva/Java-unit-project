package lection1;

public class CashbackHackService {
    private final int boundary = 1000;

    public int remain(int amount) {

        // Если сумма покупок уже кратна 1000, возвращаем 0
        if (amount >= boundary && amount % boundary == 0) {
            return 0;
        }
        // Возвращаем сумму, которую нужно добавить до следующей тысячи
        return boundary - amount % boundary;

        //return boundary - amount % boundary;
    }
}
