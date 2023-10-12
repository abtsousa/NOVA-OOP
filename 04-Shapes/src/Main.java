import shapes.*;
import java.util.Scanner;

/**
 * A simple application for shapes collection management
 *  @author Afonso Brás Sousa (LEI n.º 65263)
 *  @author Alexandre Cristóvão (LEI n.º 65143)
 */

public class Main {
    //Command-defining constants
    public static final String CIRCLE_ADD = "CIRCLE";
    public static final String RECTANGLE_ADD = "RECTANGLE";
    public static final String LIST = "LIST";
    public static final String MOVE = "MOVE";
    public static final String MINIMUM_AREA = "MINAREA";
    public static final String EXIT = "EXIT";



    //User messages constants
    public static final String CIRCLE_ADDED = "A new circle was added.\n";
    public static final String RECTANGLE_ADDED = "A new rectangle was added.\n";
    public static final String IDENTIFIER_EXIST = "Identifier already exists.\n";
    public static final String IDENTIFIER_NOT_EXIST = "Identifier does not exist.\n";
    public static final String LIST_COLLECTION = "All shapes in the collection:";
    public static final String EMPTY_COLLECTION = "Empty collection.\n";
    public static final String SHAPE_MOVED = "Shape was moved.\n";
    public static final String QUIT_MSG = "Exiting...\n";

    /**
     * Main
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        //Initialize the shape collection
        ShapesCollection sc = new ShapesCollectionClass();

        //Command interpreter
        Scanner in = new Scanner(System.in);
        cmdIntrp(in, sc);

        //Quit message
        System.out.println(QUIT_MSG);
        in.close();
    }

    /**
     * Interprets commands
     *
     * @param in scanner input
     */
    private static void cmdIntrp(Scanner in, ShapesCollection sc) {
        String comm;
        do {
            comm = getCommand(in);
            switch (comm) {
                case CIRCLE_ADD:
                    addCircle(in, sc);
                    break;
                case RECTANGLE_ADD:
                    addRectangle(in, sc);
                    break;
                case LIST:
                    listShapes(sc);
                    break;
                case MOVE:
                    moveShape(in, sc);
                    break;
                case MINIMUM_AREA:
                    getSmallestShape(sc);
                    break;
            }
        } while (!comm.equals(EXIT));
    }

    /**
     * Converts user input into a command
     *
     * @param in scanner input
     * @return user command (uppercase)
     */
    private static String getCommand(Scanner in) {
        return in.next().toUpperCase();
    }

    /**
     * Adds a new circle to the shapes collection
     *
     * @param in scanner input
     * @param sc the shapes collection
     */
    private static void addCircle(Scanner in, ShapesCollection sc) {
        String id = in.next(); //circle's ID
        int centerX = in.nextInt(); //circle center's x coordinate
        int centerY = in.nextInt(); //circle center's y coordinate
        int radius = in.nextInt(); //circle's radius
        in.nextLine();

        if (sc.hasShape(id)) System.out.println(IDENTIFIER_EXIST);
        else {
            sc.addCircle(id, centerX, centerY, radius);
            System.out.println(CIRCLE_ADDED);
        }
    }

    /**
     * Adds a new rectangle to the shapes collection
     *
     * @param in scanner input
     * @param sc the shapes collection
     */
    private static void addRectangle(Scanner in, ShapesCollection sc) {
        String id = in.next(); //rectangle's ID
        int centerX = in.nextInt(); //rectangle center's x coordinate
        int centerY = in.nextInt(); //rectangle center's y coordinate
        int height = in.nextInt(); //rectangle's height
        int width = in.nextInt(); //rectangle's width
        in.nextLine();

        if (sc.hasShape(id)) System.out.println(IDENTIFIER_EXIST);
        else {
            sc.addRectangle(id, centerX, centerY, height, width);
            System.out.println(RECTANGLE_ADDED);
        }
    }

    /**
     * List the shapes collection
     *
     * @param sc the shapes collection
     */
    private static void listShapes(ShapesCollection sc) {
        if (sc.isEmpty()) System.out.println(EMPTY_COLLECTION);
        else {
            System.out.println(LIST_COLLECTION);
            sc.listShapes();
            System.out.println();
        }
    }

    /**
     * Move a shape
     *
     * @param in scanner input
     * @param sc the shapes collection
     */
    private static void moveShape(Scanner in, ShapesCollection sc) {
        String id = in.next(); //shape's ID
        int centerX = in.nextInt(); //shape center's new x coordinate
        int centerY = in.nextInt(); //shape center's new y coordinate
        in.nextLine();

        if (!sc.hasShape(id)) System.out.println(IDENTIFIER_NOT_EXIST);
        else {
            sc.move(id,centerX,centerY);
            System.out.println(SHAPE_MOVED);
        }
    }

    /**
     * List the shape with the smallest area (&& most recent if >1 shape)
     *
     * @param sc the shapes collection
     */
    private static void getSmallestShape(ShapesCollection sc) {
        if (sc.isEmpty()) System.out.println(EMPTY_COLLECTION);
        else {
            sc.printShape(sc.smallestArea());
            System.out.println();
        }
    }

}