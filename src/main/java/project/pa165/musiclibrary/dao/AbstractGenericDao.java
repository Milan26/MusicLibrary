package project.pa165.musiclibrary.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of basic CRUD operations and common queries.
 * 
 * @author Milan
 * @param <T>
 */
public abstract class AbstractGenericDao<T> implements GenericDao<T> {

    private Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Set type for current context.
     * 
     * @param type      type of current context
     */
    public void setType(final Class<T> type) {

        this.type = type;
    }

    /**
     * Get session bound to a current context.
     * 
     * @return          current Session
     */
    protected Session getCurrentSession() {

        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(final T t) {
        getCurrentSession().save(t);
    }

    @Override
    public void update(final T t) {
        getCurrentSession().update(t);
    }

    @Override
    public void delete(final Long id) {
        final T obj = (T) getCurrentSession().get(type, id);
        if (obj != null) {
            getCurrentSession().delete(obj);
        }
    }

    @Override
    public T find(final Long id) {
        return (T) getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() {
        return (List<T>) getCurrentSession().createCriteria(type).list();
    }

}
