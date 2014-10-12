package project.pa165.musiclibrary.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.pa165.musiclibrary.dao.UserDao;
import project.pa165.musiclibrary.entities.User;

/**
 * Implementation of UserManager
 * 
 * @author Martin
 */
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void createUser(final User user) {
        userDao.create(user);
    }

    @Override
    @Transactional
    public void deleteUser(final Long id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void updateUser(final User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public User findUser(final Long id) {
        return userDao.find(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

}
