package cloud;
import dataStructures.*;

/**
 * Interface for User objects
 */
public interface User {

    //Getters
    /**
     * @return the user's email
     */
    String getEmail();


    /**
     * @return the user's type ("basic" or "premium")
     */
    String getType();

    /**
     * @param file the file's name
     * @return object named file
     */
    File getFile(String file);

    /**
     * Implements the CloudSharing.addFile() method in the User class
     * Adds a file to the user's files array.
     * @param file the file to be added
     */
    void addFile(File file);


    /**
     * Lists all the user's files.
     * @return file iterator with the user's files
     */
    Iterator<File> listFiles(); //implements listFiles()

    /**
     * Returns the free space of the account (in MB)
     * @return available storage space (in MB)
     */
    int getAvailableSpace();
}