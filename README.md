# Inventory Management System

Authentic Assessment in OOPROG — De La Salle Lipa, Computer Science Department.

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
| `Main.java` | Menu loop and all screen output. Talks to the user only. |
| `Inventory.java` | Stores the items and does the data work (add, find, remove, filter, sort). Never prints. |
| `model/Item.java` | One item. Private fields with getters; only quantity and price have setters. |
| `model/Category.java` | The three categories: Clothing, Electronics, Entertainment. |
| `util/Validator.java` | Every input rule, plus the prompts that keep asking until the value is valid. |

## Features

1. **Add Item** — category, unique ID, name, quantity, price
2. **Update Item** — update the quantity or the price of an existing item
3. **Remove Item** — remove by ID
4. **Display Items by Category** — table of one category
5. **Display All Items** — full table
6. **Search Item** — item details by ID
7. **Sort Items** — by quantity or price, ascending or descending
8. **Display Low Stock Items** — items with 5 pcs or below
9. **Exit**

## Validation rules

| Input | Rule |
|-------|------|
| Menu choice | Whole number 1–9 only |
| Category | Must be Clothing, Electronics or Entertainment (case-insensitive) |
| ID | 3–20 characters, letters and numbers, `-` and `_` allowed inside, must be unique |
| Name | 2–40 characters, must contain a letter, no double spaces |
| Quantity | Whole number 0–1,000,000 — no decimals, no negatives, no letters |
| Price | 0–1,000,000 with at most 2 decimal places — no negatives, no commas, no symbols |
| Sort / Update field | Must be `quantity` or `price` |
| Sort order | Must be `ascending` or `descending` |

Invalid input re-asks the same question instead of crashing or dropping the user
back to the menu. The two messages required by the specification —
`Category <input-value> does not exist!` and `Item not found!` — print once and
return to the main menu.
