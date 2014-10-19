package project.pa165.musiclibrary.services;

import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.entities.User;

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
        getUserDao().create(user);
    }

    @Override
    public void deleteUser(final Long id) {
        getUserDao().delete(id);
    }

    @Override
    public void updateUser(final User user) {
        getUserDao().update(user);
    }

    @Override
    public User findUser(final Long id) {
        return getUserDao().find(id);
    }

    @Override
    public List<User> getAllUsers() {
        return getUserDao().getAll();
    }

}
