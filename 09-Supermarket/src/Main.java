/**
 * A supermarket client purchases management
 *  @author Afonso Brás Sousa (LEI n.º 65263)
 *  @author Alexandre Cristóvão (LEI n.º 65143)
 */

import Exceptions.*;
import Supermarket.Item;
import Supermarket.Supermarket;
import Supermarket.SupermarketClass;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Programa principal para a aplicacao Supermarket.Supermarket.
 */
public class Main {

    //Enumerado de mensagens
    private enum Message {
        CART_CREATED("Carrinho criado com sucesso."),
        CART_ALREADY_EXISTS("Carrinho existente!"),
        CART_NOT_EXIST("Carrinho inexistente!"),
        ITEM_CREATED("Artigo criado com sucesso."),
        ITEM_ALREADY_EXISTS("Artigo existente!"),
        ITEM_NOT_EXIST("Artigo inexistente!"),
        ITEM_ADDED("Artigo adicionado com sucesso."),
        ITEM_REMOVED("Artigo removido com sucesso."),
        ITEM_NOT_IN_CART("Artigo inexistente no carrinho!"),
        CAPACITY_OVERFLOW("Capacidade excedida!"),
        CART_EMPTY("Carrinho vazio!"),
        EXIT("Volte sempre.");

        private final String msg;

        @Override
        public String toString() {
            return msg;
        }

        Message(String msg) {
            this.msg = msg;
        }
    }

    // Enumerado que define os comandos
    private enum Command {
        ADD_CART("CRIA CARRINHO"), ADD_ITEM("CRIA ARTIGO"),
        ADD_TO_CART("DEPOSITA"), REMOVE_FROM_CART("REMOVE"),
        LIST_CART("LISTA"), PAY("PAGA"),
        QUIT("SAIR"), UNKNOWN_COMMAND("");

        private final String commandInputName;

        Command(String commandInputName) {
            this.commandInputName = commandInputName;
        }

        private String getCommandInputName() {
            return commandInputName;
        }
    }

    public static void main(String[] args) {Main.execute_commands();}

    /**
     * Interpretador de comandos.
     */
    private static void execute_commands() {
        Scanner in = new Scanner(System.in);
        Supermarket sp = new SupermarketClass();
        Command comm = readCommand(in);
        while (!comm.equals(Command.QUIT)) {
            switch (comm) {
                case ADD_CART -> addCart(in, sp);
                case ADD_ITEM -> addItem(in, sp);
                case ADD_TO_CART -> addToCart(in, sp);
                case REMOVE_FROM_CART -> removeFromCart(in, sp);
                case LIST_CART -> listCart(in, sp);
                case PAY -> pay(in, sp);
            }
            System.out.println();
            comm = readCommand(in);
        }
        System.out.println(Message.EXIT);
        System.out.println();
        in.close();
    }

    /**
     * Lê o novo comando a executar.
     * @param in input de onde os dados vao ser lidos.
     */
    private static Command readCommand(Scanner in) {
        String input = in.next().toUpperCase();
        if (input.equals("CRIA"))   {input+= " "+in.next();}
        for (Command c : Command.values())
            if (c.getCommandInputName().equals(input))
                return c;
        return Command.UNKNOWN_COMMAND;
    }

    /**
     * Cria um carrinho no sistema.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai criar um carrinho no sistema.
     */
    private static void addCart(Scanner in, Supermarket sp)   {
        String cartId = in.next();
        int volume = in.nextInt();
        in.nextLine();
        try {
            sp.createCart(cartId, volume);
            System.out.println(Message.CART_CREATED);
        } catch (CartAlreadyExistsException e)  {
            System.out.println(Message.CART_ALREADY_EXISTS);
        }
    }

    /**
     * Cria um artigo no sistema.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai criar um artigo no sistema.
     */
    private static void addItem(Scanner in, Supermarket sp)   {
        String itemName = in.next();
        int price = in.nextInt();
        int volume = in.nextInt();
        in.nextLine();
        try {
            sp.createItem(itemName, price, volume);
            System.out.println(Message.ITEM_CREATED);
        } catch (ItemAlreadyExistsException e)  {
            System.out.println(Message.ITEM_ALREADY_EXISTS);
        }
    }

    /**
     * Adiciona um artigo do carrinho.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai adicionar um artigo a um carrinho.
     */
    private static void addToCart(Scanner in, Supermarket sp)   {
        String itemName = in.next();
        String cartId = in.next().trim();

        try {
            sp.addItemToCart(itemName, cartId);
            System.out.println(Message.ITEM_ADDED);
        } catch (Exception e)   {
            if (e instanceof CartNotExistException) {System.out.println(Message.CART_NOT_EXIST);}
            else if (e instanceof ItemNotExistException) {System.out.println(Message.ITEM_NOT_EXIST);}
            else System.out.println(Message.CAPACITY_OVERFLOW);
        }
    }

    /**
     * Remove um artigo do carrinho.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai remover um artigo do carrinho.
     */
    private static void removeFromCart(Scanner in, Supermarket sp)   {
        String itemName = in.next();
        String cartId = in.next().trim();
        in.nextLine();
        try {
            sp.removeItemFromCart(itemName, cartId);
            System.out.println(Message.ITEM_REMOVED);
        } catch (Exception e)   {
            if (e instanceof CartNotExistException) {System.out.println(Message.CART_NOT_EXIST);}
            else if (e instanceof ItemNotInCartException) {System.out.println(Message.ITEM_NOT_IN_CART);}
                else System.out.println(Message.ITEM_NOT_EXIST);
        }
    }

    /**
     * Lista o conteúdo de um carrinho.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai listar o conteúdo de um carrinho
     */
    private static void listCart(Scanner in, Supermarket sp)   {
        String cartId = in.nextLine().trim();

        try {
            Iterator<Item> it = sp.listCartItems(cartId);
            while (it.hasNext())    {
                Item i = it.next();
                System.out.printf("%s %d\n", i.getName(),i.getPrice());
            }
        } catch (Exception e)   {
            if (e instanceof CartIsEmptyException)  {
                System.out.println(Message.CART_EMPTY);
            } else {
                System.out.println(Message.CART_NOT_EXIST);
            }
        }
    }

    /**
     * Paga um carrinho.
     * @param in input de onde os dados vao ser lidos.
     * @param sp Supermarket onde se vai pagar um carrinho.
     */
    private static void pay(Scanner in, Supermarket sp)   {
        String cartId = in.next().trim();
        in.nextLine();
        try {
            System.out.println(sp.payCart(cartId));
        } catch (Exception e)  {
            if (e instanceof CartNotExistException)    {
                System.out.println(Message.CART_NOT_EXIST);
            } else {
                System.out.println(Message.CART_EMPTY);
            }
        }
    }

}

