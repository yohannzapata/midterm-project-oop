# Inventory Management System

Authentic Assessment in OOPROG, De La Salle Lipa, Computer Science Department.

A console-based inventory management system in Java built around **encapsulation**
and **abstraction**.

## How to run

```bash
javac Main.java Inventory.java model/*.java util/*.java
java Main
```

## Project structure

| File | Responsibility |
|------|----------------|
| `Main.java` | Menu loop, input flow, and screen output. |
| `Inventory.java` | Stores items and handles add, find, remove, filter, and sort operations. |
| `model/Item.java` | Represents one item with private fields, getters, and setters for quantity and price. |
| `model/Category.java` | The three categories: Clothing, Electronics, Entertainment. |
| `util/Validator.java` | Every input rule, plus the prompts that keep asking until the value is valid. |

## Features

1. **Add Item:** category, unique ID, name, quantity, price
2. **Update Item:** update the quantity or the price of an existing item
3. **Remove Item:** remove by ID
4. **Display Items by Category:** table of one category
5. **Display All Items:** full table
6. **Search Item:** item details by ID
7. **Sort Items:** by quantity or price, ascending or descending
8. **Display Low Stock Items:** items with 5 pcs or below
9. **Exit**

## Validation rules

| Input | Rule |
|-------|------|
| Menu choice | Whole number from 1 to 9 only |
| Category | Must be Clothing, Electronics or Entertainment (case-insensitive) |
| ID | 3 to 20 characters, letters and numbers, `-` and `_` allowed inside, must be unique |
| Name | 2 to 40 characters, must contain a letter, no double spaces |
| Quantity | Whole number from 0 to 10,000, with no decimals, negatives, letters, or symbols |
| Price | 0 to 1,000,000 with at most 2 decimal places, with no negatives, commas, or symbols |
| Sort / Update field | Must be `quantity` or `price` |
| Sort order | Must be `ascending` or `descending` |

Invalid menu, ID, name, quantity, price, sort, and update inputs re-ask the same
question instead of crashing or returning to the menu. Leading and trailing
whitespace is removed from text input before validation. Names may contain
single spaces and common punctuation, but not repeated spaces. Category input is
case-insensitive. An unknown category prints `Category <input-value> does not
exist!` and returns to the main menu. A missing item prints `Item not found!` and
returns to the main menu.
