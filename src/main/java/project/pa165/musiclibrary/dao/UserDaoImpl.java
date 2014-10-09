package project.pa165.musiclibrary.dao;

import org.springframework.stereotype.Repository;
import project.pa165.musiclibrary.entities.User;

/**
 *
 * @author Milan
 */
@Repository
public class UserDaoImpl extends AbstractGenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        setType(User.class);
    }
    
}
