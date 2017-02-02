package org.jinq.sscce;

import org.jinq.jpa.JPAQueryLogger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.sscce.jpa.SampleDbCreator;
import org.jinq.sscce.jpa.entities.SubClass;
import org.jinq.sscce.jpa.entities.SuperClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Member;
import java.util.Map;


public class Main {
    private static EntityManagerFactory entityManagerFactory;
    private static JinqJPAStreamProvider streams;

    private static EntityManager em;

    public static void main(String[] args) throws Exception {
        // Configure Jinq for the given JPA database connection
        entityManagerFactory = Persistence.createEntityManagerFactory("sscce");
        SampleDbCreator dbCreator = new SampleDbCreator(entityManagerFactory);
        dbCreator.initDatabase();

        // init Jinq
        streams = new JinqJPAStreamProvider(entityManagerFactory);

        // Configure Jinq to output the queries it executes
        streams.setHint("queryLogger", new JPAQueryLogger() {
            @Override
            public void logQuery(String query, Map<Integer, Object> positionParameters,
                                 Map<String, Object> namedParameters) {
                System.out.println("  " + query);
            }
        });

        // register methods
        streams.registerAssociationAttribute(SuperClass.class.getMethod("isBoolTrue"), "bool", false);
        streams.registerAssociationAttribute(SubClass.class.getMethod("isBoolTrue"), "bool", false);
        System.out.println("Methods registered successfully");

        em = entityManagerFactory.createEntityManager();
        streams.streamAll(em, SubClass.class)
            .where(s -> s.isBoolTrue())
            .forEach(System.out::println);
    }
}
