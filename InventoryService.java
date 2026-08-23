import java.util.Scanner;

import model.Category;
import model.Item;

public class InventoryService {
    public static Scanner sc = new Scanner(System.in);

    Inventory inventory = new Inventory();

    public void add() {
        System.out.println("Enter Category: ");
        Category[] categories = Category.values();

        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }

        System.out.println("Enter your choice (1-3): ");
        int choice = sc.nextInt();
        Category category = categories[choice - 1];

        System.out.println("Enter Category: ");
        String id = sc.nextLine();
        System.out.println("Enter Category: ");
        String name = sc.nextLine();
        System.out.println("Enter Category: ");
        int quantity = sc.nextInt();
        System.out.println("Enter Category: ");
        double price = sc.nextDouble();

        Item newItem = new Item(id, name, quantity, price, category);
        inventory.add(newItem);

        System.out.println("Item Added Successfuly!");
    }
}
