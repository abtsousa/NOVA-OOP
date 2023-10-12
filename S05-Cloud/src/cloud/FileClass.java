package cloud;

/**
 * Simple file class.
 */
public class FileClass implements File {
    //Instance variables
    private final String name; //the file's name
    private final int size; //the file's size
    private final String owner; //the file's owner

    /**
     * Constructor
     * @param name the file's name
     * @param size the file's size
     * @param owner the file's owner
     */
    public FileClass(String name, int size, String owner) {
        this.name = name;
        this.size = size;
        this.owner = owner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getOwner() {
        return owner;
    }
}
