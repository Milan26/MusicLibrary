package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.DaoException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of UserManager
 *
 * @author Martin
 */
@Named
@Transactional
public class UserManagerImpl implements UserManager {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(final User user) {
        try {
            getUserDao().create(user);
        } catch (DaoException e) {

        }
    }

    @Override
    public void deleteUser(final Long id) {
        try {
            getUserDao().delete(id);
        } catch (DaoException e) {

        }
    }

    @Override
    public void updateUser(final User user) {
        try {
            getUserDao().update(user);
        } catch (DaoException e) {

        }
    }

    @Override
    public User findUser(final Long id) {
        User user = null;
        try {
            user = getUserDao().find(id);
        } catch (DaoException e) {

        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            allUsers = getUserDao().getAll();
        } catch (DaoException e) {

        }
        return allUsers;
    }

}
