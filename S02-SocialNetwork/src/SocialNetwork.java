/**
 * The interface for our social network
 */
public interface SocialNetwork {

    /**
     * Lists all registered users.
     * Pre: not empty
     */
    void listUsers();

    /**
     * Returns the index of the requested user.
     * @param name the user's name
     * @return user index or NOT_FOUND if not found
     */
    int getUserIndex(String name);

    /**
     * Returns the user object associated with the name
     * Pre: hasUser(name); name is unique
     * @param name the user's name
     * @return user object associated with that name
     */
    User getUser(String name);

    /**
     * Checks if the user is already registered.
     * @param name the user's name
     * @return true if user is registered
     */
    boolean hasUser(String name);

    /**
     * Registers a new user.
     * Pre: !hasUser(name)
     * @param name the user's name
     * @param email the user's email address
     * @param status the user's initial status message.
     */
    void newUser(String name, String email, String status);

    /**
     * Checks if the users are friends with each other.
     * @param userA the first user
     * @param userB the second user
     * @return true if they are friends
     */
    boolean areFriends(String userA, String userB);

    /**
     * Registers friendship — Adds each user to the other's friends list.
     * @param userA the first user
     * @param userB the second user
     */
    void registerFriends(String userA, String userB);

    /**
     * Checks if the social network has no users
     * @return true if empty
     */
    boolean isEmpty();

    /**
     *
     *
     */
    void listMural();
}
