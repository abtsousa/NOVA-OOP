/**
 * A basic iterator
 */
public interface Iterator {
    /**
     * Initializes the iterator (sets counter to zero).
     */
    void init();

    /**
     * @return true if there's an element left in the array.
     */
    boolean hasNext();

    /**
     * @return the next element
     */
    User next();
}
