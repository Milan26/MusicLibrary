package project.pa165.musiclibrary.services;

import java.util.List;
import project.pa165.musiclibrary.entities.User;

/**
 *
 * @author Martin
 */
public interface UserManager {

    /**
     * Insert given user.
     *
     * @param user     user to be inserted
     */
    void createUser(User user);

    /**
     * Delete user by given id.
     *
     * @param id        identification of user to be deleted
     */
    void deleteUser(Long id);

    /**
     * Update given user.
     *
     * @param user     user to be updated
     */
    void updateUser(User user);

    /**
     * Get user by given id.
     *
     * @param id        identification of user to be found
     * @return          found user
     */
    User findUser(Long id);

    /**
     * Get all users.
     *
     * @return          list of all users
     */
    List<User> getAllUsers();

}
