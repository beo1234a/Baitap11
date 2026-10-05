package anhtuan.vn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import anhtuan.vn.config.JPAConfig_24133072;
import anhtuan.vn.entity.Video_24133072;

public class VideoDAO_24133072
        implements IVideoDAO_24133072 {

    @Override
    public List<Video_24133072> findAll() {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT v "
                    + "FROM Video_24133072 v "
                    + "LEFT JOIN FETCH v.category "
                    + "ORDER BY v.videoId",
                    Video_24133072.class)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public Video_24133072 findById(String id) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            List<Video_24133072> videos =
                    em.createQuery(
                            "SELECT v "
                            + "FROM Video_24133072 v "
                            + "LEFT JOIN FETCH v.category "
                            + "WHERE v.videoId = :videoId",
                            Video_24133072.class)
                            .setParameter(
                                    "videoId",
                                    id)
                            .getResultList();

            return videos.isEmpty()
                    ? null
                    : videos.get(0);

        } finally {
            em.close();
        }
    }

    @Override
    public void insert(Video_24133072 video) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {

            transaction.begin();

            em.persist(video);

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
    public void update(Video_24133072 video) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {

            transaction.begin();

            em.merge(video);

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
    public void delete(String id) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        EntityTransaction transaction =
                em.getTransaction();

        try {

            transaction.begin();

            em.createQuery(
                    "DELETE FROM Favorite_24133072 f "
                    + "WHERE f.video.videoId = :videoId")
                    .setParameter(
                            "videoId",
                            id)
                    .executeUpdate();

            em.createQuery(
                    "DELETE FROM Share_24133072 s "
                    + "WHERE s.video.videoId = :videoId")
                    .setParameter(
                            "videoId",
                            id)
                    .executeUpdate();

            Video_24133072 video =
                    em.find(
                            Video_24133072.class,
                            id);

            if (video != null) {
                em.remove(video);
            }

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
    public List<Video_24133072> findPage(
            int page,
            int pageSize) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT v "
                    + "FROM Video_24133072 v "
                    + "LEFT JOIN FETCH v.category "
                    + "ORDER BY v.videoId",
                    Video_24133072.class)
                    .setFirstResult(
                            (page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public long count() {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(v) "
                    + "FROM Video_24133072 v",
                    Long.class)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }

    @Override
    public List<Video_24133072> findByCategoryPage(
            Integer categoryId,
            int page,
            int pageSize) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT v "
                    + "FROM Video_24133072 v "
                    + "LEFT JOIN FETCH v.category "
                    + "WHERE v.category.categoryId = :categoryId "
                    + "AND v.active = true "
                    + "ORDER BY v.videoId",
                    Video_24133072.class)
                    .setParameter(
                            "categoryId",
                            categoryId)
                    .setFirstResult(
                            (page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    @Override
    public long countByCategory(
            Integer categoryId) {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT COUNT(v) "
                    + "FROM Video_24133072 v "
                    + "WHERE v.category.categoryId = :categoryId "
                    + "AND v.active = true",
                    Long.class)
                    .setParameter(
                            "categoryId",
                            categoryId)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }

    @Override
    public String generateVideoId() {

        EntityManager em =
                JPAConfig_24133072.getEntityManager();

        try {

            List<String> ids =
                    em.createQuery(
                            "SELECT v.videoId "
                            + "FROM Video_24133072 v",
                            String.class)
                            .getResultList();

            int max = 0;

            for (String id : ids) {

                if (id == null) {
                    continue;
                }

                String number =
                        id.replaceAll(
                                "[^0-9]",
                                "");

                if (number.isEmpty()) {
                    continue;
                }

                try {

                    int value =
                            Integer.parseInt(number);

                    if (value > max) {
                        max = value;
                    }

                } catch (NumberFormatException e) {
                    // Bỏ qua ID không đúng định dạng
                }
            }

            return String.format(
                    "VID%03d",
                    max + 1);

        } finally {
            em.close();
        }
    }
}