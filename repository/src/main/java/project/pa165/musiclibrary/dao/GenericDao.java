package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.exception.DaoException;

import java.util.List;

/**
 * Base interface for CRUD operations and common queries.
 *
 * @param <T> entity type
 * @author Milan
 */
public interface GenericDao<T> {

    /**
     * Insert object of type T.
     *
     * @param t object to be inserted
     */
    void create(T t) throws DaoException;

    /**
     * Update object of type T.
     *
     * @param t object to be updated
     */
    void update(T t) throws DaoException;

    /**
     * Delete object with given id.
     *
     * @param id identification of object to be deleted
     */
    void delete(Long id) throws DaoException;

    /**
     * Get object of type T with given id.
     *
     * @param id identification of object to be found
     * @return found object
     */
    T find(Long id) throws DaoException;

    /**
     * Get all objects of type T.
     *
     * @return list of all object
     */
    List<T> getAll() throws DaoException;

}
