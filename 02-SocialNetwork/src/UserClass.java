/**
 * User class
 */
public class UserClass implements User {
    private String name;
    private String email;
    private String status;
    private User[] friends; //Pre: <50

    private int size; //Pre: <500

    //Constants
    public final int NOT_FOUND = -1; //index to return if no user found

    /**
     * Constructor
     * @param name the user's name
     * @param email the user's email
     * @param status the user's status
     */
    public UserClass(String name, String email, String status) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.friends = new User[]{};
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void changeStatus(String newStatus) {
        status = newStatus;
    }

    @Override
    public Iterator getFriends() {
        return new UserIterator(friends, friends.length);
    }
    private boolean isFull() {
        return (size == friends.length);
    }

    public boolean isEmpty() {
        return (friends.length == 0);
    }

    @Override
    public void listFriends() {
        Iterator it = getFriends();
        while (it.hasNext()) {
            User user = it.next();
            System.out.printf("%s; %s\n",user.getName(),user.getEmail());
        }
    }

    @Override
    public void newFriend(User user) {
        if (isFull()) {growFriends();}
            friends[size-1] = user;
        }
    /**
     * Grows the friend array
     */
    private void growFriends() {
        User[] newFriends = new User[friends.length+1];
        int i=0;
        while (i<friends.length) {
            newFriends[i] = friends[i];
            i++;
        }
        friends = newFriends;
        size++;
    }

    @Override
    public int getFriendIndex(String name) {
        Iterator friendIt = getFriends(); //start iterator
        int i=0;
        while (friendIt.hasNext()) {
            User user = friendIt.next();
            if (user.getName().equals(name)) {return i;}
            else i++;
        }
        return NOT_FOUND;
    }

}
