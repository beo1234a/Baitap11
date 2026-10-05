package anhtuan.vn.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAConfig_24133072 {

    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("De03_24133072");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void close() {
        if (FACTORY != null && FACTORY.isOpen()) {
            FACTORY.close();
        }
    }
}