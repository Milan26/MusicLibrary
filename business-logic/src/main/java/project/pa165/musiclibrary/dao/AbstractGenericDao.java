package project.pa165.musiclibrary.dao;

import project.pa165.musiclibrary.exception.PersistenceException;

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
    public void create(final T t) throws PersistenceException {
        try {
            getEntityManager().persist(t);
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

    @Override
    public void update(final T t) throws PersistenceException {
        try {
            getEntityManager().merge(t);
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(final Long id) throws PersistenceException {
        final T obj = find(id);
        try {
            if (obj != null) {
                getEntityManager().remove(obj);
            }
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(final Long id) throws PersistenceException {
        try {
            return getEntityManager().find(type, id);
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() throws PersistenceException {
        try {
            return getEntityManager().createQuery("FROM " + type.getName(), type).getResultList();
        } catch (Exception ex) {
            throw new PersistenceException(ex);
        }
    }

}
