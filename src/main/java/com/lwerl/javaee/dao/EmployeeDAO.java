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

    public List<Employee> getAll(String search) {
        if (search == null || search.isEmpty()) {
            return getAll();
        } else {
            return entityManager.createQuery(
                    "select e from Employee e join e.department d join e.position p "
                            + "where LOWER( e.login) like :search "
                            + "or LOWER(e.firstName) like :search "
                            + "or LOWER(e.middleName) like :search "
                            + "or LOWER(e.lastName) like :search "
                            + "or LOWER(d.city) like :search "
                            + "or LOWER(d.name) like :search "
                            + "or LOWER(p.name) like :search "
                    , Employee.class)
                    .setParameter("search","%" + search.toLowerCase() + "%")
                    .getResultList();
        }

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
