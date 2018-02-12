package com.lwerl.javaee.cdi;


import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by lWeRl on 12.02.2018.
 */
public class EntityManagerProducer {

    @Produces
    public EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("jpa").createEntityManager();
    }

    public void close(
            @Disposes EntityManager entityManager) {
        entityManager.close();
    }


}
