package br.com.hibernate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    public static EntityManagerFactory factory = null;

    static {
        init();
    }
    private static void init() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("javahibernate");
        }
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static Object getPrimaryKey(Object entity) {
        return factory.getPersistenceUnitUtil().getIdentifier(entity);
    }
}