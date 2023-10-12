package shapes;

/**
 * Generic circle class
 * Extends basic AbstractShape
 */
public class CircleClass extends AbstractShape {
    //Instance variables
    private final int radius;

    /**
     * Constructor
     * @param id unique identifier
     * @param centerX center's x coordinate
     * @param centerY center's y coordinate
     * @param radius circle's radius
     */
    public CircleClass(String id, int centerX, int centerY, int radius) {
        super(id, centerX, centerY);
        this.radius = radius;
    }

    /**
     * Calculates the circle's area
     * @return the circle's area
     */
    public double area()    {
        return Math.PI*Math.pow(radius,2);
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }
}
