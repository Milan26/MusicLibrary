package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.entities.User;

/**
 * Base interface for operations and queries on User entity.
 *
 * @author Milan
 */
public interface UserDao extends GenericDao<User> {

    User findUserByEmail(String email);
}
