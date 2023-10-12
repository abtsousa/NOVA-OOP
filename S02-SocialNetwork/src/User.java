public interface User {
    //Getters
    /**
     * @return the name of the user
     */
    String getName();

    /**
     * @return the email of the user
     */
    String getEmail();

    /**
     * @return the status message of the user
     */
    String getStatus();

    /**
     * Changes the status message of the user
     * @param newStatus the user's new status
     */
    void changeStatus(String newStatus);

    /**
     * Returns the user's friends
     * @return iterator with the user's friends
     */
    Iterator getFriends();

    /**
     * Checks if the user has no friends
     * @return true if empty
     */
    boolean isEmpty();

    /**
     * Lists all registered friends.
     * Pre: not empty
     */
    void listFriends();

    /**
     * Registers a new friend.
     * Pre: !hasUser(name)
     * @param user the friends's name
     */
    void newFriend(User user);

    /**
     * Returns the index of the requested friend.
     * @param name the friends's name
     * @return user index or NOT_FOUND if not found
     */
    int getFriendIndex(String name);
}