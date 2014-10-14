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

    @Inject
    private UserDao userDao;

    @Override
    public void createUser(final User user) {
        userDao.create(user);
    }

    @Override
    public void deleteUser(final Long id) {
        userDao.delete(id);
    }

    @Override
    public void updateUser(final User user) {
        userDao.update(user);
    }

    @Override
    public User findUser(final Long id) {
        return userDao.find(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

}
