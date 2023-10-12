package Supermarket;

import java.util.List;

public interface Cart {
    String getCartId();
    int getRemainingCapacity();
    boolean isFull();
    boolean isEmpty();
    void addItem(Item item);
    void removeItem(Item item);
    void purge();
    boolean hasItem(Item item);
    List<Item> getItems();
    int getValue();
}
