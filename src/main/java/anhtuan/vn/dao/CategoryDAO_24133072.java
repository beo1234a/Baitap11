package anhtuan.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;

import anhtuan.vn.config.JPAConfig_24133072;
import anhtuan.vn.entity.Category_24133072;

public class CategoryDAO_24133072 implements ICategoryDAO_24133072 {

    @Override
    public List<Category_24133072> findAll() {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Category_24133072 c ORDER BY c.categoryId",
                    Category_24133072.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Category_24133072 findById(Integer id) {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            return em.find(Category_24133072.class, id);
        } finally {
            em.close();
        }
    }
}