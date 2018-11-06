package com.lwerl.javaee.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by lWeRl on 12.02.2018.
 */
public interface DAO<T, I> {

    T get(I id);
    List<T> getAll();
    I save(T model);
    void update(T model);
    void delete(T model);

    static <T> void withTransaction(EntityManager manager, T arg, Consumer<T> func) throws RuntimeException {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            func.accept(arg);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
