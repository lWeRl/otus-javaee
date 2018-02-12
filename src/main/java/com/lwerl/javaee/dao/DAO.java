package com.lwerl.javaee.dao;

import java.util.List;

/**
 * Created by lWeRl on 12.02.2018.
 */
public interface DAO<T, I> {

    T get(I id);
    List<T> getAll();
    I save(T model);
    void update(T model);
    void delete(T model);

}
