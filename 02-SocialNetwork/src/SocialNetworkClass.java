/**
 * System class, implements the main Social Network interface.
 */
public class SocialNetworkClass implements SocialNetwork {
    //Instance variables
    private User[] users;
    private int size; //Pre: <500

    //Constants
    public final int NOT_FOUND = -1; //index to return if no user found

    /**
     * Constructor
     */
    public SocialNetworkClass() {
        users = new User[]{};
        size = 0;
    }

    @Override
    public int getUserIndex(String name) {
        Iterator userIt = userIt(); //start iterator
        int i=0;
        while (userIt.hasNext()) {
            User user = userIt.next();
            if (user.getName().equals(name)) {return i;}
            else i++;
        }
        return NOT_FOUND;
    }

    @Override
    public User getUser(String name) {
        int index = getUserIndex(name);
        return users[index];
    }

    //Iterator methods
    private Iterator userIt() {
        return new UserIterator(users, size);
    }

    @Override
    public void listUsers() {
        Iterator it = userIt();
        while (it.hasNext()) {
            User user = it.next();
            System.out.printf("%s; %s\n",user.getName(),user.getEmail());
        }
    }

    /*
     * Array size management
     */

    /**
     * @return true if the user array is full
     */
    private boolean isFull() {
        return (size == users.length);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Grows the user array
     */
    private void growUsers() {
        User[] newUsers = new User[users.length+1];
        int i=0;
        while (i<users.length) {
            newUsers[i] = users[i];
            i++;
        }
        users = newUsers;
        size++;
    }

    @Override
    public boolean hasUser(String name) {
        return (getUserIndex(name) != NOT_FOUND);
    }

    @Override
    public void newUser(String name, String email, String status) {
        User user = new UserClass(name, email, status);
        if (isFull()) {growUsers();}
        users[size-1] = user;
    }

    @Override
    public boolean areFriends(String userA, String userB) {
        return (getUser(userA).getFriendIndex(userB) != NOT_FOUND);
    }

    @Override
    public void registerFriends(String userA, String userB) {
        getUser(userA).newFriend(getUser(userB));
        getUser(userB).newFriend(getUser(userA));



    }
}
