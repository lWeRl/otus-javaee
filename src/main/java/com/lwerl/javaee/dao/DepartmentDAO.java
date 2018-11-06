package com.lwerl.javaee.dao;

import com.lwerl.javaee.model.Department;
import com.lwerl.javaee.model.Position;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lWeRl on 12.02.2018.
 */
@Singleton
public class DepartmentDAO implements DAO<Department, Long> {

    private EntityManager entityManager;

    @Inject
    public DepartmentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Department get(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> getAll() {
        return entityManager.createQuery("from Department", Department.class).getResultList();
    }

    @Override
    public Long save(Department model) {
        DAO.withTransaction(entityManager, model, entityManager::persist);
        return model.getId();
    }

    @Override
    public void update(Department model) {
        DAO.withTransaction(entityManager, model, entityManager::merge);
    }

    @Override
    public void delete(Department model) {
        DAO.withTransaction(entityManager, get(model.getId()), entityManager::remove);
    }
}
