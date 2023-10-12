package cloud;

import dataStructures.Iterator;

/**
 * A basic user
 */
public class BasicUserClass extends AbstractUserClass {

    /**
     * Constructor
     * @param email            the user's email
     */
    protected BasicUserClass(String email) {
        super(email);
        capacity = 1024*2; //2 GB
        sharedFilesRatio = 0.5; //shared files count 50% of their size towards the limit
    }

    @Override
    public String getType() {
        return "Basic";
    }

}
