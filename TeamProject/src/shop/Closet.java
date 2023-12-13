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
    
    // 메소드 오버로딩
    public void addItem(List<Item> newItems) {
        for (Item item : newItems) {
            if (!items.contains(item)) {
                items.add(item);
            }
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    public List<Item> getWornItems() {
        List<Item> wornItems = new ArrayList<>();
        for (Item item : items) {
            if (item.isWorn()) {
                wornItems.add(item);
            }
        }
        return wornItems;
    }
}
