package util;

import java.util.List;
import java.util.Scanner;

import model.Category;

public class Validator {

    public static final int ID_MIN_LENGTH = 3;
    public static final int ID_MAX_LENGTH = 20;
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 40;
    public static final int MAX_QUANTITY = 1_000_000;
    public static final double MAX_PRICE = 1_000_000.00;

    private Validator() {
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidId(String value) {
        if (isBlank(value)) {
            return false;
        }
        String id = value.trim();
        return id.length() >= ID_MIN_LENGTH
                && id.length() <= ID_MAX_LENGTH
                && id.matches("[A-Za-z0-9]+([-_][A-Za-z0-9]+)*");
    }

    public static boolean isUniqueId(String value, List<String> existingIds) {
        for (String existing : existingIds) {
            if (existing.equalsIgnoreCase(value.trim())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidName(String value) {
        if (isBlank(value)) {
            return false;
        }
        String name = value.trim();
        return name.length() >= NAME_MIN_LENGTH
                && name.length() <= NAME_MAX_LENGTH
                && name.matches("[A-Za-z0-9][A-Za-z0-9 .,'&()/+-]*")
                && name.matches(".*[A-Za-z].*")
                && !name.contains("  ");
    }

    public static Category parseCategory(String value) {
        if (isBlank(value)) {
            return null;
        }
        String input = value.trim();
        for (Category category : Category.values()) {
            if (category.getLabel().equalsIgnoreCase(input)) {
                return category;
            }
        }
        return null;
    }

    public static boolean isValidQuantity(String value) {
        if (isBlank(value) || !value.trim().matches("\\d{1,7}")) {
            return false;
        }
        int quantity = Integer.parseInt(value.trim());
        return quantity >= 0 && quantity <= MAX_QUANTITY;
    }

    public static boolean isValidPrice(String value) {
        if (isBlank(value) || !value.trim().matches("\\d{1,7}(\\.\\d{1,2})?")) {
            return false;
        }
        double price = Double.parseDouble(value.trim());
        return price >= 0.0 && price <= MAX_PRICE;
    }

    public static boolean isValidChoice(String value, int minimum, int maximum) {
        if (isBlank(value) || !value.trim().matches("\\d{1,2}")) {
            return false;
        }
        int choice = Integer.parseInt(value.trim());
        return choice >= minimum && choice <= maximum;
    }

    public static boolean isOneOf(String value, String first, String second) {
        if (isBlank(value)) {
            return false;
        }
        String input = value.trim();
        return input.equalsIgnoreCase(first) || input.equalsIgnoreCase(second);
    }

    private static String ask(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readChoice(Scanner scanner, String prompt, int minimum, int maximum) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isValidChoice(value, minimum, maximum)) {
                return Integer.parseInt(value.trim());
            }
            System.out.println("Invalid input! Enter a number from " + minimum + " to " + maximum + ".");
        }
    }

    public static String readNewId(Scanner scanner, String prompt, List<String> existingIds) {
        while (true) {
            String value = ask(scanner, prompt);
            if (!isValidId(value)) {
                System.out.println("Invalid ID! Use " + ID_MIN_LENGTH + " to " + ID_MAX_LENGTH
                        + " characters: letters and numbers only (- and _ allowed inside).");
            } else if (!isUniqueId(value, existingIds)) {
                System.out.println("ID '" + value.trim() + "' is already used by another item.");
            } else {
                return value.trim();
            }
        }
    }

    public static String readExistingId(Scanner scanner, String prompt) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isValidId(value)) {
                return value.trim();
            }
            System.out.println("Invalid ID! Use " + ID_MIN_LENGTH + " to " + ID_MAX_LENGTH
                    + " characters: letters and numbers only (- and _ allowed inside).");
        }
    }

    public static String readName(Scanner scanner, String prompt) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isValidName(value)) {
                return value.trim();
            }
            System.out.println("Invalid name! Use " + NAME_MIN_LENGTH + " to " + NAME_MAX_LENGTH
                    + " characters, must include a letter, no double spaces.");
        }
    }

    public static int readQuantity(Scanner scanner, String prompt) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isValidQuantity(value)) {
                return Integer.parseInt(value.trim());
            }
            System.out.println("Invalid quantity! Enter a whole number from 0 to " + String.format("%,d", MAX_QUANTITY) + ".");
        }
    }

    public static double readPrice(Scanner scanner, String prompt) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isValidPrice(value)) {
                return Double.parseDouble(value.trim());
            }
            System.out.println("Invalid price! Enter an amount from 0 to " + String.format("%,.2f", MAX_PRICE)
                    + " with at most 2 decimal places.");
        }
    }

    public static String readWord(Scanner scanner, String prompt, String first, String second) {
        while (true) {
            String value = ask(scanner, prompt);
            if (isOneOf(value, first, second)) {
                return value.trim().toLowerCase();
            }
            System.out.println("Invalid input! Type '" + first + "' or '" + second + "'.");
        }
    }

    public static String readCategoryInput(Scanner scanner, String prompt) {
        return ask(scanner, prompt).trim();
    }
}
