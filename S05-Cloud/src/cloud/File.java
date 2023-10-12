package cloud;

/**
 * Simple file interface with 3 getters
 * Contains 3 variables: the file's name, size and owner.
 */
public interface File {
    /**
     * @return the file's name
     */
    String getName();

    /**
     * @return the file's size
     */
    int getSize();

    /**
     * @return the file's owner
     */
    String getOwner();
}
