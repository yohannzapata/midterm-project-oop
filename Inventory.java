import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Item;

/**
 * Stores inventory items and provides operations for lookup, filtering, and sorting.
 * This class manages data only and does not print user-facing messages.
 */
public class Inventory {

    public static final int LOW_STOCK_LIMIT = 5;

    private final ArrayList<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    /** Finds an item by ID, ignoring letter case, or returns {@code null}. */
    public Item findById(String id) {
        for (Item item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    public boolean existsById(String id) {
        return findById(id) != null;
    }

    /** Removes and returns the item with the supplied ID, or returns {@code null}. */
    public Item removeById(String id) {
        Item item = findById(id);
        if (item != null) {
            items.remove(item);
        }
        return item;
    }

    public List<String> getAllIds() {
        List<String> ids = new ArrayList<>();
        for (Item item : items) {
            ids.add(item.getId());
        }
        return ids;
    }

    public List<Item> getAll() {
        return new ArrayList<>(items);
    }

    public List<Item> getByCategory(Category category) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getCategory() == category) {
                result.add(item);
            }
        }
        return result;
    }

    /** Returns a new list containing items at or below the low-stock threshold. */
    public List<Item> getLowStock() {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getQuantity() <= LOW_STOCK_LIMIT) {
                result.add(item);
            }
        }
        return result;
    }

    /** Returns a sorted copy without changing the order of the stored inventory. */
    public List<Item> getSorted(boolean byQuantity, boolean ascending) {
        List<Item> sorted = new ArrayList<>(items);

        for (int i = 0; i < sorted.size() - 1; i++) {
            int chosen = i;
            for (int j = i + 1; j < sorted.size(); j++) {
                if (comesFirst(sorted.get(j), sorted.get(chosen), byQuantity, ascending)) {
                    chosen = j;
                }
            }
            if (chosen != i) {
                Item temp = sorted.get(i);
                sorted.set(i, sorted.get(chosen));
                sorted.set(chosen, temp);
            }
        }
        return sorted;
    }

    private boolean comesFirst(Item candidate, Item current, boolean byQuantity, boolean ascending) {
        double left = byQuantity ? candidate.getQuantity() : candidate.getPrice();
        double right = byQuantity ? current.getQuantity() : current.getPrice();
        return ascending ? left < right : left > right;
    }
}
