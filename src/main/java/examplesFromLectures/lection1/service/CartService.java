package examplesFromLectures.lection1.service;

import examplesFromLectures.lection1.model.PurchaseItem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CartService {
    private final List<PurchaseItem> items = new ArrayList<>();


    public void add(PurchaseItem item) {
        items.add(item);
    }

    public List<PurchaseItem> sortedBy(Comparator<PurchaseItem> comparator) {
        List<PurchaseItem> result = new LinkedList<>(items);
        result.sort(comparator);
        return result;

    }

}
