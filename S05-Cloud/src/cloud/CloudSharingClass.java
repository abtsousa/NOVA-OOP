package cloud;

import dataStructures.*;

/**
 * System class
 */
public class CloudSharingClass implements CloudSharing {

    //Constants
    private static final String USERTYPE_PREMIUM = "PREMIUM";
    private static final String USERTYPE_BASIC = "BASIC";

    //Instance variables
    private final Array<User> users;

    /**
     * Constructor
     */
    public CloudSharingClass() {
        users = new ArrayClass<User>();
    }

    @Override
    public User getUser(String email) {
        Iterator<User> it = listAll();
        while (it.hasNext()) {
            User user = it.next();
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    @Override
    public boolean hasUser(String email) {
        return (getUser(email) != null);
    }

    @Override
    public void addUser(String email, String type) {
        User user = null;
        if (type.equals(USERTYPE_BASIC)) {user = new BasicUserClass(email);}
        else if (type.equals(USERTYPE_PREMIUM)) {user = new PremiumUserClass(email);}
        users.insertLast(user);
    }

    @Override
    public void addFile(String user, String file, int size) {
        getUser(user).addFile(new FileClass(file, size, user));
    }

    @Override
    public void shareFile(String owner, String other, String file) {
        getUser(other).addFile(getUser(owner).getFile(file));
    }

    @Override
    public boolean hasOwnedFile(String user, String file) {
        Iterator<File> it = listFiles(user);
        boolean found = false;
        while (it.hasNext() && !found) {
            found = it.next().getName().equals(file);
        }
        return found;
    }

    @Override
    public boolean hasSharedFile(String owner, String other, String file) {
        Iterator<File> it = listFiles(other);
        File f;
        boolean found = false;
        while (it.hasNext() && !found) {
            f = it.next();
            found = ( f.getOwner().equals(owner) ) && ( f.getName().equals(file) );
        }
        return found;

    }

    @Override
    public boolean hasCapacity(String user, int size) {
        return getUser(user).getAvailableSpace()>=size;
    }

    @Override
    public boolean hasSharingCapacity(String user, String other, String file) {
        return getUser(other).getAvailableSpace()>=getUser(user).getFile(file).getSize();
    }

    @Override
    public boolean allowsSharing(String owner) {
        return getUser(owner).getType().toUpperCase().equals(USERTYPE_PREMIUM);
    }

    @Override
    public Iterator<File> listFiles(String user) {
        return getUser(user).listFiles();
    }

    @Override
    public Iterator<User> listAll() {
        return users.iterator();
    }
}
