package model;

public class Item {

    private final String id;
    private final String name;
    private final Category category;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFormattedQuantity() {
        return quantity + " pcs";
    }

    public String getFormattedPrice() {
        return String.format("Php %,.2f", price);
    }
}
