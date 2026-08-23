import java.util.Map;

import model.Item;

public class Inventory {

    private final Map<String, Item> items;
    
    public void add(Item item) {
        items.put(item.id, item);
    }

    public void remove(Item item) {
        items.remove(item.id, item);
    }

    // public Item findById(int id) {
    // }

    
}
