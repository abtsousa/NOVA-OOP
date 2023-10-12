package Supermarket;
import Exceptions.*;

import java.util.*;

public class SupermarketClass implements Supermarket{

    //VARIABLES
    private Map<String, Cart> carts; //Cart contém uma capacidade e uma List<Supermarket.Item>
    private Map<String, Item> items; //Item contém um preço e um volume

    //CONSTRUCTOR
    public SupermarketClass() {
        this.carts = new HashMap<>();
        this.items = new HashMap<>();
    }

    //METHDOS
    @Override
    public void createCart(String cartId, int volume) throws CartAlreadyExistsException {
        if (!hasCart(cartId)) {
            Cart cart = new CartClass(cartId, volume);
            carts.put(cartId, cart);
        } else {throw new CartAlreadyExistsException();}
    }

    @Override
    public void createItem(String itemName, int price, int volume) throws ItemAlreadyExistsException {
        if (!hasItem(itemName))   {
            Item item = new ItemClass(itemName, price, volume);
            items.put(itemName, item);
        } else {throw new ItemAlreadyExistsException();}
    }

    @Override
    public void addItemToCart(String itemName, String cartId) throws CartNotExistException, ItemNotExistException, CartOverflowException {
        if (!hasCart(cartId))    {throw new CartNotExistException();}
        else if (!hasItem(itemName))    {throw new ItemNotExistException();}
        else if (getCart(cartId).getRemainingCapacity()<getItem(itemName).getVolume())  {throw new CartOverflowException();}
        else { getCart(cartId).addItem(getItem(itemName));
        }
    }

    @Override
    public void removeItemFromCart(String itemName, String cartId) throws CartNotExistException, ItemNotExistException, ItemNotInCartException {
        if (!hasCart(cartId))    {throw new CartNotExistException();}
        else if (!getCart(cartId).hasItem(getItem(itemName)))    {throw new ItemNotInCartException();}
        else if (!hasItem(itemName))  {throw new ItemNotExistException();}
        else { getCart(cartId).removeItem(getItem(itemName));
        }
    }

    @Override
        public ListIterator<Item> listCartItems(String cartId) throws CartIsEmptyException, CartNotExistException {
        if (getCart(cartId).isEmpty()) {throw new CartIsEmptyException();}
        else if (!hasCart(cartId)){ throw new CartNotExistException();}
        else return getCart(cartId).getItems().listIterator(0);
        }

    @Override
    public int payCart(String cartId) throws CartIsEmptyException, CartNotExistException {
        if (!hasCart(cartId))    {throw new CartNotExistException();}
        else if (getCart(cartId).isEmpty()) {throw new CartIsEmptyException();}
        else {
            int price = getCart(cartId).getValue();
            getCart(cartId).purge();
            return price;
        }
        }

    @Override
    public void emptyCart(Cart cart) {
        cart.purge();
    }

    @Override
    public boolean hasCart(String cartId) {
        return carts.containsKey(cartId);

    }

    @Override
    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    @Override
    public Cart getCart(String cartId) {
        return carts.get(cartId);
    }

    @Override
    public Item getItem(String itemName) {
        return items.get(itemName);
    }
}
