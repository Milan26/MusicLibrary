/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pa165.musiclibrary.dao;

import java.util.List;

/**
 *
 * @author Milan
 */
public interface GenericDao<T> {

    void create(T t);

    void update(T t);

    void delete(Long id);

    T find(Long id);
    
    List<T> getAll();

}
