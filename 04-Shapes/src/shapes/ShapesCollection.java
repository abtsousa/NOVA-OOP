package shapes;

/**
 * Interface for generic array of Shapes
 */
public interface ShapesCollection {

	/**
	 * @return true if the shapes array is empty
	 */
	boolean isEmpty();

	/**
	 * @return true if the shape exists in the array
	 * @param id the shape's id
	 */
	boolean hasShape(String id);

	/**
	 * Add a new circle to the shapes array
	 * @pre: !hasShape(id)
	 * @param id unique identifier
	 * @param x center's x coordinate
	 * @param y center's y coordinate
	 * @param radius circle's radius
	 */
	void addCircle(String id, int x, int y, int radius);

	/**
	 * Add a new rectangle to the shapes array
	 * @pre: !hasShape(id)
	 * @param id unique identifier
	 * @param x center's x coordinate
	 * @param y center's y coordinate
	 * @param height rectangle's height
	 * @param width rectangle's width
	 */
	void addRectangle(String id, int x, int y, int height, int width);

	/**
	 * Move the shape
	 * @pre: hasShape(id)
	 * @param id unique identifier
	 * @param x center's new x coordinate
	 * @param y center's new y coordinate
	 */
	void move(String id, int x, int y);

	/**
	 * Identifies the shape with the smallest area (&& most recent if >1 shape)
	 */
	Shape smallestArea();

	/**
	 * Shapes Collection Iterator
	 */
	Iterator allShapesIterator();

	void listShapes();

	void printShape(Shape shape);
}
