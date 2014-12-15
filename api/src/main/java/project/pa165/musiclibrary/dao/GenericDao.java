package project.pa165.musiclibrary.dao;

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
    void create(T t);

    /**
     * Update object of type T.
     *
     * @param t object to be updated
     */
    void update(T t);

    /**
     * Delete object with given id.
     *
     * @param id identification of object to be deleted
     */
    void delete(Long id);

    /**
     * Get object of type T with given id.
     *
     * @param id identification of object to be found
     * @return found object
     */
    T find(Long id);

    /**
     * Get all objects of type T.
     *
     * @return list of all object
     */
    List<T> getAll();
}
