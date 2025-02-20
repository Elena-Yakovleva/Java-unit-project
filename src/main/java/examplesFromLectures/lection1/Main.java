package examplesFromLectures.lection1;

import examplesFromLectures.lection1.model.PurchaseItem;
import examplesFromLectures.lection1.service.CartService;
import lombok.val;

public class Main {
    public static void main(String[] args) {

//        val item = new PurchaseItem(1,700,1);
//        System.out.println(item);

        CartService service = new CartService();
        service.add(new PurchaseItem(1, 1_000, 1));
        service.add(new PurchaseItem(2, 4_000, 3));
        service.add(new PurchaseItem(3, 6_000, 1));
        service.add(new PurchaseItem(4, 11_000, 6));
        service.add(new PurchaseItem(5, 2_000, 2));
        service.add(new PurchaseItem(6, 3_000, 1));
        service.add(new PurchaseItem(7, 5_000, 4));


    }
}
