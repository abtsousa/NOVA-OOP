package shapes;

/**
 * System class, implements the Shapes collection interface
 */
public class ShapesCollectionClass implements ShapesCollection {
    //Instance variables
    private Shape[] shapes;
    private int size;

    //Constants
    public final int NOT_FOUND = -1; //index to return if no user found

    /**
     * Constructor
     */
    public ShapesCollectionClass() {
        size = 0;
        shapes = new Shape[]{};
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean hasShape(String id) {
        return (getShapeIndex(id) != NOT_FOUND);
    }

    @Override
    public Iterator allShapesIterator() {
        return new ShapeIterator(shapes, size);
    }

    /**
     * @param id the requested shape's id
     * @return the index of the requested class.
     */
    public int getShapeIndex(String id) {
        Iterator shapeIt = allShapesIterator(); //start iterator
        int i=0;
        while (shapeIt.hasNext()) {
            Shape shape = shapeIt.next();
            if (shape.getID().equals(id)) {return i;}
            else i++;
        }
        return NOT_FOUND;
    }

    public Shape getShape(String id) {
        int index = getShapeIndex(id);
        return shapes[index];
    }

    @Override
    public void addCircle(String id, int x, int y, int radius) {
        addShape(new CircleClass(id, x, y, radius));
    }

    @Override
    public void addRectangle(String id, int x, int y, int height, int width) {
        addShape(new RectangleClass(id, x, y, height, width));
    }

    private void addShape(AbstractShape shape) {
        if (isFull()) {growShapes();}
        shapes[size-1] = shape;
    }

    @Override
    public void move(String id, int x, int y) {
        Shape shape = getShape(id);
        shape.move(x,y);
    }

    @Override
    public Shape smallestArea() {
        Iterator shapeIt = allShapesIterator(); //start iterator
        Shape smallshape = shapeIt.next();
        while (shapeIt.hasNext()) {
            Shape shape = shapeIt.next();
            if (shape.area()<=smallshape.area()) smallshape = shape;
        }
        return smallshape;
    }

    public void listShapes() {
        Iterator it = allShapesIterator();
        while (it.hasNext()) {
            Shape shape = it.next();
            printShape(shape);
        }
    }

    public void printShape(Shape shape) {
        System.out.printf("%s (%d, %d) %s\n", shape.getID(), shape.getCenterX(), shape.getCenterY(), shape.getType());
    }

    //Array size management
    /**
     * @return true if the shapes array is full
     */
    private boolean isFull() {
        return (size == shapes.length);
    }

    /**
     * Grows the shapes array
     */
    private void growShapes() {
        Shape[] newShapes = new Shape[shapes.length+1];
        int i=0;
        while (i<shapes.length) {
            newShapes[i] = shapes[i];
            i++;
        }
        shapes = newShapes;
        size++;
    }
}
