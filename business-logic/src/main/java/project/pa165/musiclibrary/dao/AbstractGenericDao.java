package project.pa165.musiclibrary.dao;

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
    public void create(final T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void update(final T t) {
        getEntityManager().merge(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(final Long id) {
        final T obj = find(id);
        if (obj != null) {
            getEntityManager().remove(obj);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T find(final Long id) {
        return getEntityManager().find(type, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return getEntityManager().createQuery("FROM " + type.getName(), type).getResultList();
    }

}
