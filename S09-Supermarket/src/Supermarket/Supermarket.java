package Supermarket;

import Exceptions.*;

import java.util.ListIterator;

public interface Supermarket {
    void createCart(String cartId, int volume) throws CartAlreadyExistsException;
    void createItem(String itemName, int price, int volume) throws ItemAlreadyExistsException;
    void addItemToCart(String itemName, String cartId) throws CartNotExistException, ItemNotExistException, CartOverflowException;
    void removeItemFromCart(String itemName, String cartId) throws CartNotExistException, ItemNotExistException, ItemNotInCartException;
    ListIterator<Item> listCartItems(String cartId) throws CartIsEmptyException, CartNotExistException;
    int payCart(String cartId) throws CartIsEmptyException, CartNotExistException;
    void emptyCart(Cart cart);

    boolean hasCart(String cartId);
    boolean hasItem(String itemName);
    Cart getCart(String cartId);
    Item getItem(String itemName);

}
