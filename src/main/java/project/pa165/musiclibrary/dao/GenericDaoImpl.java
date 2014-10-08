/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Milan
 * @param <T>
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public void setType(final Class<T> type) {

        this.type = type;
    }

    protected final Session getCurrentSession() {

        return sessionFactory.getCurrentSession();
    }

    @Override
    public void create(T t) {
        getCurrentSession().save(t);
    }

    @Override
    public void update(T t) {
        getCurrentSession().update(t);
    }

    @Override
    public void delete(Long id) {
        T obj = (T) getCurrentSession().get(type, id);
        if (null != obj) {
            getCurrentSession().delete(obj);
        }
    }

    @Override
    public T find(Long id) {
        return (T) getCurrentSession().get(type, id);
    }

    @Override
    public List<T> getAll() {
        return (List<T>) getCurrentSession().createCriteria(type).list();
    }

}
