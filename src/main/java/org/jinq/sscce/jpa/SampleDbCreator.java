package org.jinq.sscce.jpa;


import org.jinq.sscce.jpa.entities.SubClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SampleDbCreator {
    private EntityManager em;

    public SampleDbCreator(EntityManagerFactory factory) {
        this.em = factory.createEntityManager();
    }

    public void initDatabase() {
        em.getTransaction().begin();

        SubClass sub = new SubClass();
        sub.setName("Name1");
        sub.setBool(true);
        em.persist(sub);

        em.getTransaction().commit();
    }
}
