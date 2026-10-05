package anhtuan.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import anhtuan.vn.config.JPAConfig_24133072;
import anhtuan.vn.entity.User_24133072;

public class UserDAO_24133072 implements IUserDAO_24133072 {

    @Override
    public User_24133072 findByUsername(String username) {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            return em.find(User_24133072.class, username);
        } finally {
            em.close();
        }
    }

    @Override
    public User_24133072 findByEmail(String email) {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            List<User_24133072> users = em.createQuery(
                    "SELECT u FROM User_24133072 u WHERE u.email = :email",
                    User_24133072.class)
                    .setParameter("email", email)
                    .getResultList();

            return users.isEmpty() ? null : users.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(User_24133072 user) {
        EntityManager em = JPAConfig_24133072.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User_24133072 user) {
        EntityManager em = JPAConfig_24133072.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}