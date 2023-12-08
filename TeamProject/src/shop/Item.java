package shop;

public class Item {
    private String name;
    private int price;
    private boolean soldOut;
    private String category;
    private boolean worn;

    public Item(String name, int price, String category) {
        this.name = name;
        this.price = price;
        this.soldOut = false;
        this.category = category;
        this.worn = false;
        
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public boolean isWorn() {
        return worn;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public void setWorn(boolean worn) {
        this.worn = worn;
    }
}