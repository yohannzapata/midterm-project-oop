import java.util.List;
import java.util.Scanner;

import model.Category;
import model.Item;
import util.Validator;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final Inventory inventory = new Inventory();

    private static final int BANNER_WIDTH = 45;
    private static final String TABLE_HEADER_FULL =
            String.format("%-12s | %-24s | %12s | %16s | %-14s", "ID", "NAME", "QUANTITY", "PRICE", "CATEGORY");
    private static final String TABLE_HEADER_SHORT =
            String.format("%-12s | %-24s | %12s | %16s", "ID", "NAME", "QUANTITY", "PRICE");

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = Validator.readChoice(sc, "Enter your choice (1-9): ", 1, 9);

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    displayByCategory();
                    break;
                case 5:
                    displayAllItems();
                    break;
                case 6:
                    searchItem();
                    break;
                case 7:
                    sortItems();
                    break;
                case 8:
                    displayLowStockItems();
                    break;
                case 9:
                    running = false;
                    message("Thank you for using the Inventory Management System!");
                    break;
                default:
                    break;
            }
        }
        sc.close();
    }

    private static void addItem() {
        header("ADD ITEM");

        Category category = askCategory();
        if (category == null) {
            return;
        }

        String id = Validator.readNewId(sc, "Enter ID: ", inventory.getAllIds());
        String name = Validator.readName(sc, "Enter Name: ");
        int quantity = Validator.readQuantity(sc, "Enter Quantity: ");
        double price = Validator.readPrice(sc, "Enter Price: ");

        inventory.addItem(new Item(id, name, quantity, price, category));
        message("Item added successfully!");
    }

    private static void updateItem() {
        header("UPDATE ITEM");

        if (isInventoryEmpty()) {
            return;
        }

        String id = Validator.readExistingId(sc, "Enter ID: ");
        Item item = inventory.findById(id);
        if (item == null) {
            message("Item not found!");
            return;
        }

        showItemDetails(item);

        String field = Validator.readWord(sc, "Update Quantity or Price? ", "quantity", "price");

        if (field.equals("quantity")) {
            int oldValue = item.getQuantity();
            int newValue = Validator.readQuantity(sc, "Enter new Quantity: ");
            item.setQuantity(newValue);
            message("Quantity of Item " + item.getName() + " is updated from " + oldValue + " to " + newValue);
        } else {
            double oldValue = item.getPrice();
            double newValue = Validator.readPrice(sc, "Enter new Price: ");
            item.setPrice(newValue);
            message("Price of Item " + item.getName() + " is updated from "
                    + String.format("%,.2f", oldValue) + " to " + String.format("%,.2f", newValue));
        }
    }

    private static void removeItem() {
        header("REMOVE ITEM");

        if (isInventoryEmpty()) {
            return;
        }

        String id = Validator.readExistingId(sc, "Enter ID: ");
        Item removed = inventory.removeById(id);

        if (removed == null) {
            message("Item not found!");
        } else {
            message("Item " + removed.getName() + " has been removed from the inventory");
        }
    }

    private static void displayByCategory() {
        header("DISPLAY ITEMS BY CATEGORY");

        Category category = askCategory();
        if (category == null) {
            return;
        }

        List<Item> items = inventory.getByCategory(category);
        if (items.isEmpty()) {
            message("No items found under " + category.getLabel() + ".");
            return;
        }
        printTable(category.getLabel().toUpperCase() + " ITEMS", items, false);
    }

    private static void displayAllItems() {
        if (isInventoryEmpty()) {
            return;
        }
        printTable("ALL ITEMS", inventory.getAll(), true);
    }

    private static void searchItem() {
        header("SEARCH ITEM");

        if (isInventoryEmpty()) {
            return;
        }

        String id = Validator.readExistingId(sc, "Enter ID: ");
        Item item = inventory.findById(id);

        if (item == null) {
            message("Item not found!");
        } else {
            showItemDetails(item);
        }
    }

    private static void sortItems() {
        header("SORT ITEMS");

        if (isInventoryEmpty()) {
            return;
        }

        String field = Validator.readWord(sc, "Sort by Quantity or Price? ", "quantity", "price");
        String order = Validator.readWord(sc, "Ascending or Descending? ", "ascending", "descending");

        boolean byQuantity = field.equals("quantity");
        boolean ascending = order.equals("ascending");

        String title = "ITEMS SORTED BY " + field.toUpperCase() + " (" + order.toUpperCase() + ")";
        printTable(title, inventory.getSorted(byQuantity, ascending), true);
    }

    private static void displayLowStockItems() {
        if (isInventoryEmpty()) {
            return;
        }

        List<Item> items = inventory.getLowStock();
        if (items.isEmpty()) {
            message("No low stock items. Every item is above " + Inventory.LOW_STOCK_LIMIT + " pcs.");
            return;
        }
        printTable("LOW STOCK ITEMS (" + Inventory.LOW_STOCK_LIMIT + " pcs and below)", items, true);
    }

    private static Category askCategory() {
        System.out.println("Categories: " + Category.listAll());

        String input = Validator.readCategoryInput(sc, "Enter Category: ");
        while (input.isEmpty()) {
            System.out.println("Invalid input! Category is required.");
            input = Validator.readCategoryInput(sc, "Enter Category: ");
        }

        Category category = Validator.parseCategory(input);
        if (category == null) {
            message("Category " + input + " does not exist!");
        }
        return category;
    }

    private static boolean isInventoryEmpty() {
        if (inventory.isEmpty()) {
            message("The inventory is empty. Add an item first.");
            return true;
        }
        return false;
    }

    private static void showMenu() {
        System.out.println();
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println(center("Inventory Management System", BANNER_WIDTH));
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println("1. Add Item");
        System.out.println("2. Update Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Display Items by Category");
        System.out.println("5. Display All Items");
        System.out.println("6. Search Item");
        System.out.println("7. Sort Items");
        System.out.println("8. Display Low Stock Items");
        System.out.println("9. Exit");
        System.out.println(line('=', BANNER_WIDTH));
    }

    private static void showItemDetails(Item item) {
        System.out.println();
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println(" ITEM DETAILS");
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println(" ID       : " + item.getId());
        System.out.println(" Name     : " + item.getName());
        System.out.println(" Quantity : " + item.getFormattedQuantity());
        System.out.println(" Price    : " + item.getFormattedPrice());
        System.out.println(" Category : " + item.getCategory().getLabel());
        System.out.println(line('=', BANNER_WIDTH));
    }

    private static void printTable(String title, List<Item> items, boolean withCategory) {
        String head = withCategory ? TABLE_HEADER_FULL : TABLE_HEADER_SHORT;
        String border = line('=', head.length());

        System.out.println();
        System.out.println(border);
        System.out.println(" " + title);
        System.out.println(border);
        System.out.println(head);
        System.out.println(line('-', head.length()));

        for (Item item : items) {
            if (withCategory) {
                System.out.println(String.format("%-12s | %-24s | %12s | %16s | %-14s",
                        item.getId(), item.getName(), item.getFormattedQuantity(),
                        item.getFormattedPrice(), item.getCategory().getLabel()));
            } else {
                System.out.println(String.format("%-12s | %-24s | %12s | %16s",
                        item.getId(), item.getName(), item.getFormattedQuantity(),
                        item.getFormattedPrice()));
            }
        }

        System.out.println(border);
        System.out.println(" Total items: " + items.size());
        System.out.println(border);
    }

    private static void header(String title) {
        System.out.println();
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println(" " + title);
        System.out.println(line('=', BANNER_WIDTH));
    }

    private static void message(String text) {
        System.out.println();
        System.out.println(line('=', BANNER_WIDTH));
        System.out.println(" " + text);
        System.out.println(line('=', BANNER_WIDTH));
    }

    private static String line(char symbol, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(symbol);
        }
        return builder.toString();
    }

    private static String center(String text, int width) {
        int padding = (width - text.length()) / 2;
        return line(' ', Math.max(padding, 0)) + text;
    }
}
