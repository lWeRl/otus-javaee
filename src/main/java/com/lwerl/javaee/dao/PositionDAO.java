package com.lwerl.javaee.dao;

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
public class PositionDAO implements DAO<Position, Long> {

    private EntityManager entityManager;

    @Inject
    public PositionDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Position get(Long id) {
        return entityManager.find(Position.class, id);
    }

    @Override
    public List<Position> getAll() {
        return entityManager.createQuery("from Position", Position.class).getResultList();
    }

    @Override
    public Long save(Position model) {
        DAO.withTransaction(entityManager, model, entityManager::persist);
        return model.getId();
    }

    @Override
    public void update(Position model) {
        DAO.withTransaction(entityManager, model, entityManager::merge);
    }

    @Override
    public void delete(Position model) {
        DAO.withTransaction(entityManager, get(model.getId()), entityManager::remove);
    }
}
