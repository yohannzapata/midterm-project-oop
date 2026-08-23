package model;

public class Item {
    public String id;
    public String name;
    public int quantity;
    public double price;
    public Category category;

    public Item(String id, String name, int quantity, double price, Category category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }
}