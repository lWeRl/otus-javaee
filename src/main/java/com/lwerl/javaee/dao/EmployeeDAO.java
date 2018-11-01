package com.lwerl.javaee.dao;

import com.lwerl.javaee.model.Employee;
import org.hibernate.Session;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by lWeRl on 12.02.2018.
 */
@Singleton
public class EmployeeDAO implements DAO<Employee, Long> {

    private EntityManager entityManager;

    @Inject
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee get(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAll() {
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }

    @Override
    public Long save(Employee model) {
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        return model.getId();
    }

    @Override
    public void update(Employee model) {
        entityManager.getTransaction().begin();
        entityManager.merge(model);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Employee model) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(model.getId()));
        entityManager.getTransaction().commit();
    }

    public String getMaxSalaryName() {
        return (String) entityManager.createStoredProcedureQuery("getMaxSalaryName").getSingleResult();
    }
}
