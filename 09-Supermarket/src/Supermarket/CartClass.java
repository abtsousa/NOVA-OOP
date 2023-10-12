package Supermarket;

import java.util.ArrayList;
import java.util.List;

public class CartClass implements Cart{

    //VARIABLES
    private String cartId;
    private int volume;
    private int usedCapacity;
    private int value;
    private List<Item> items;

    //CONSTRUCTOR
    CartClass(String cartId, int volume)  {
        this.cartId=cartId;
        this.volume=volume;
        this.items = new ArrayList<>();
        usedCapacity=0;
        value=0;
    }

    //METHODS
    public String getCartId()  {
        return cartId;
    }

    public int getRemainingCapacity()    {
        return volume-usedCapacity;
    }

    public boolean isFull() {
        return volume==usedCapacity;
    }

    @Override
    public boolean isEmpty() {
        return usedCapacity==0;
    }

    public void addItem(Item item)   {
        items.add(item);
        value += item.getPrice();
        usedCapacity += item.getVolume();
    }

    public void removeItem(Item item)   {

        items.remove(item);
        value -= item.getPrice();
        usedCapacity -= item.getVolume();
    }

    public void purge() {
        items.clear();
        usedCapacity=0;
        value=0;
    }

    @Override
    public boolean hasItem(Item item) {
        return items.contains(item);
    }
    public List<Item> getItems()  {
        return items;
    }

    public int getValue()   {
        return value;
    }
}
