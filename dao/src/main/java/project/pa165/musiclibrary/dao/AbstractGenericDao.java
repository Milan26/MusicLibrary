package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.exception.DaoException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of basic CRUD operations and common queries.
 *
 * @param <T>
 * @author Milan
 */
public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    private Class<T> type;
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Set type for current context.
     *
     * @param type type of current context
     */
    public void setType(final Class<T> type) {
        this.type = type;
    }

    @Override
    public void create(final T t) throws DaoException {
        try {
            getEntityManager().persist(t);
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void update(final T t) throws DaoException {
        try {
            getEntityManager().merge(t);
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(final Long id) throws DaoException {
        final T obj = find(id);
        try {
            if (obj != null) {
                getEntityManager().remove(obj);
            }
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(final Long id) throws DaoException {
        try {
            return getEntityManager().find(type, id);
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() throws DaoException {
        try {
            return getEntityManager().createQuery("FROM " + type.getName(), type).getResultList();
        } catch (Exception exception) {
            throw new DaoException(exception);
        }
    }

}
