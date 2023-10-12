package shapes;

/**
 * Generic rectangle class
 * Extends basic AbstractShape
 */
public class RectangleClass extends AbstractShape {
    //Instance variables
    private final int height, width;

    /**
     * Constructor
     * @param id unique identifier
     * @param centerX center's x coordinate
     * @param centerY center's y coordinate
     * @param height rectangle's height
     * @param width rectangle's width
     */
    public RectangleClass(String id, int centerX, int centerY, int height, int width) {
        super(id, centerX, centerY);
        this.height = height;
        this.width = width;
    }

    /**
     * Calculates the rectangle's area
     * @return the rectangle's area
     */
    public double area()    {
        return height*width;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }
}
