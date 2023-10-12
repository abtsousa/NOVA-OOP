package Supermarket;

public class ItemClass implements Item{

    //VARIABLES
    private String itemName;
    private int price;
    private int volume;

    //CONSTRUCTOR
    ItemClass(String itemName, int price, int volume) {
        this.itemName=itemName;
        this.price=price;
        this.volume=volume;
    }

    //METHODS
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getVolume() {
        return volume;
    }
    @Override
    public String getName() {
        return itemName;
    }
}

