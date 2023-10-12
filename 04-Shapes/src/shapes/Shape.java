package shapes;

/**
 * Generic shape
 * Holds variables for id, center x, center y and related methods
 */

public interface Shape {
	/**
	 * @return the shape's id (unique identifier)
	 */
	String getID();

	/**
	 * @return rectangle or circle
	 */
	String getType();

	/**
	 * @return the center's x coordinate
	 */
	int getCenterX();

	/**
	 * @return the center's y coordinate
	 */
	int getCenterY();

	/**
	 * Moves the center of the shape
	 * @param x the center's new x coordinate
	 * @param y the center's new y coordinate
	 */
	void move(int x, int y);

	/**
	 * @return the shape's area
	 */
	double area();
}
