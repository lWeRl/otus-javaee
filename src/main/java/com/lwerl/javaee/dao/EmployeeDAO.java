package com.lwerl.javaee.dao;

import com.lwerl.javaee.model.Employee;

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
        DAO.withTransaction(entityManager, model, entityManager::persist);
        return model.getId();
    }

    @Override
    public void update(Employee model) {
        DAO.withTransaction(entityManager, model, entityManager::merge);
    }

    @Override
    public void delete(Employee model) {
        DAO.withTransaction(entityManager, get(model.getId()), entityManager::remove);
    }

    public String getMaxSalaryName() {
        return (String) entityManager.createStoredProcedureQuery("getMaxSalaryName").getSingleResult();
    }

    @SuppressWarnings({"squid:S2068"})
    public Employee getByCredentials(String login, String password) {
        return entityManager.createQuery("from Employee where login=:login and password=:password", Employee.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }
}
