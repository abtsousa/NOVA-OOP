package cloud;

import dataStructures.*;

/**
 * Defines the premium user (can share files).
 */
public class PremiumUserClass extends AbstractUserClass {

    /**
     * Constructor
     * @param email            the user's email
     */
    protected PremiumUserClass(String email) {
        super(email);
        capacity = 1024 * 5;  //5 GB
        sharedFilesRatio = 0; //shared files do not count towards the limit
    }

    @Override
    public String getType() {
        return "Premium";
    }

}
