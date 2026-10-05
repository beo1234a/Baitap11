package anhtuan.vn.config;

import javax.persistence.EntityManager;

public class TestJPA_24133072 {

    public static void main(String[] args) {

        EntityManager entityManager = null;

        try {
            entityManager = JPAConfig_24133072.getEntityManager();

            if (entityManager != null && entityManager.isOpen()) {
                System.out.println("======================================");
                System.out.println("KET NOI JPA THANH CONG");
                System.out.println("Database: De03_24133072");
                System.out.println("MSSV: 24133072");
                System.out.println("======================================");

                Long categoryCount = entityManager
                        .createQuery(
                                "SELECT COUNT(c) FROM Category_24133072 c",
                                Long.class)
                        .getSingleResult();

                System.out.println("So luong Category: " + categoryCount);
            }

        } catch (Exception e) {
            System.out.println("======================================");
            System.out.println("KET NOI JPA THAT BAI");
            System.out.println("======================================");
            e.printStackTrace();

        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }

            JPAConfig_24133072.close();
        }
    }
}