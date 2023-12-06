package shop;

import java.util.ArrayList;
import java.util.List;

public class Closet {
    private List<Item> items;

    public Closet() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
}
