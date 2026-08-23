import java.util.*;
import java.util.Locale.Category;

import model.Item;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("=====================================");
        System.out.println("     Inventory Management System     ");
        System.out.println("=====================================");
        System.out.println("1. Add Item");
        System.out.println("2. Update Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Display Items by Category");
        System.out.println("5. Display All Items");
        System.out.println("6. Search Item");
        System.out.println("7. Sort items");
        System.out.println("8. Display Low Stock Items");
        System.out.println("9. Exit");
        System.out.println("=====================================");
        int choice;

        do {
            System.out.print("Enter your choice (1-9): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's a invalid input!");
                sc.next();
            }
            choice = sc.nextInt();
        } while (choice <= 0 || choice >= 10);
        
        switch(choice) {
            case 1:
            break;
            case 2:
            break;
            case 4:
            break;
            case 5:
            break;
            case 6:
            break;
            case 7:
            break;
            case 8:
            break;
            case 9:
            break;
        }

    }

    
}