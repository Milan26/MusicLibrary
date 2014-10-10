package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.User;

/**
 * Implementation of UserDao
 * 
 * @author Milan
 */
@Repository
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {

    /**
     * Sets specific type for genericDao.
     */
    public UserDaoImpl() {
        super();
        setType(User.class);
    }
    
}
