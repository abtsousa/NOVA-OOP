package cloud;
import dataStructures.*;

/**
 * System interface
 */
public interface CloudSharing {

    /**
     * Returns a user object
     * @pre hasUser(email)
     * @param email user's email
     * @return User object
     */
    User getUser(String email);

    /**
     * Checks if social network has a user
     * @param email email to check
     * @return true if user exists
     */
    boolean hasUser(String email);

    /**
     * Adds a user to the network
     * @param email the user's email
     * @param type the type of account
     */
    void addUser(String email, String type);

    /**
     * Adds a file to a user
     * @param user the user's email
     * @param file the file's name
     * @param size the file's size
     */
    void addFile(String user, String file, int size);

    /**
     * Shares a file with another user.
     * @pre user.hasSharingCapacity()
     * @param owner the owner of the file
     * @param other the recipient of the file
     * @param file the file to be shared
     */
    void shareFile(String owner, String other, String file);

    /**
     * Checks if user owns a file
     * @pre CloudSharing.hasUser();
     * @param user the user's email
     * @param file the file name
     * @return true if the user owns a file with that name
     */
    boolean hasOwnedFile(String user, String file);

    /**
     * Checks if user has a shared file
     * @param owner of the file
     * @param other recipient of the file
     * @param file the file name
     * @return true if recipient has already the shared file
     */
    boolean hasSharedFile(String owner, String other, String file);

    /**
     * Checks if user has enough free space
     * @param user user's email
     * @param size user's avaiable storage
     * @return true if free space >= size
     */
    boolean hasCapacity(String user, int size);

    /**
     * Checks if user has enough free space to receive shared file
     * @param user the owner of the file
     * @param other the recipient of the file
     * @param file the file to be shared
     * @return true if recipient has enough free space to receive file
     */
    boolean hasSharingCapacity(String user, String other, String file);

    /**
     * Checks if user can share files
     * @param user user's email
     * @return true if user can share files
     */
    boolean allowsSharing(String user);

    /**
     * Creates an iterator with the user's files
     * @param user user's email
     * @return a user's files
     */
    Iterator<File> listFiles(String user);

    /**
     * Creates an iterator of all users
     * @return iterator with all users
     */
    Iterator<User> listAll();
}
