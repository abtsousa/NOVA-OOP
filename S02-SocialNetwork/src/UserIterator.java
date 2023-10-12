/**
 * A simple iterator for user arrays
 */
public class UserIterator implements Iterator {
    private final User[] users;
    private final int size;
    private int index;

    /**
     * Constructor
     * @param users user array to iterate
     * @param size size of array
     */
    public UserIterator(User[] users, int size) {
        this.users = users;
        this.size = size;
        this.init();
    }

    @Override
    public void init() {
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public User next() {
        return users[index++];
    }
}
