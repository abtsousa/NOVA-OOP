package shapes;

abstract class AbstractShape implements Shape {
    //Instance variables
    private final String id;
    private int centerX, centerY;

    /**
     * Constructor
     * @param id unique identifier
     * @param centerX center's x coordinate
     * @param centerY center's y coordinate
     */
    public AbstractShape(String id, int centerX, int centerY) {
        this.id = id;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public abstract String getType();

    @Override
    public int getCenterX() {
        return centerX;
    }

    @Override
    public int getCenterY() {
        return centerY;
    }

    @Override
    public void move(int x, int y) {
        this.centerX = x;
        this.centerY = y;
    }

    @Override
    public abstract double area();
}
