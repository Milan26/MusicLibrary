package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.ServiceException;

import java.util.List;

/**
 * @author Martin
 */
public interface UserManager {

    /**
     * Insert given user.
     *
     * @param user user to be inserted
     */
    void createUser(User user) throws ServiceException;

    /**
     * Delete user by given id.
     *
     * @param id identification of user to be deleted
     */
    void deleteUser(Long id) throws ServiceException;

    /**
     * Update given user.
     *
     * @param user user to be updated
     */
    void updateUser(User user) throws ServiceException;

    /**
     * Get user by given id.
     *
     * @param id identification of user to be found
     * @return found user
     */
    User findUser(Long id) throws ServiceException;

    /**
     * Get all users.
     *
     * @return list of all users
     */
    List<User> getAllUsers() throws ServiceException;

}
