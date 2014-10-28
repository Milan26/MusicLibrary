package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.User;

import javax.inject.Named;

/**
 * Implementation of UserDao
 *
 * @author Milan
 */
@Named
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    /**
     * Sets specific type for genericDao.
     */
    public UserDaoImpl() {
        super();
        setType(User.class);
    }

}
