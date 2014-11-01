package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.entities.User;
import project.pa165.musiclibrary.exception.PersistenceException;
import project.pa165.musiclibrary.exception.ServiceException;

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
    public void createUser(final User user) throws ServiceException {
        try {
            getUserDao().create(user);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void deleteUser(final Long id) throws ServiceException {
        try {
            getUserDao().delete(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public void updateUser(final User user) throws ServiceException {
        try {
            getUserDao().update(user);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
    }

    @Override
    public User findUser(final Long id) throws ServiceException {
        User user = null;
        try {
            user = getUserDao().find(id);
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> allUsers = null;
        try {
            allUsers = getUserDao().getAll();
        } catch (PersistenceException persistenceException) {
            throw new ServiceException(persistenceException);
        }
        return allUsers;
    }

}
