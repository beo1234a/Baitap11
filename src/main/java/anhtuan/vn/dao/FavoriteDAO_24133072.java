package anhtuan.vn.dao;

import javax.persistence.EntityManager;

import anhtuan.vn.config.JPAConfig_24133072;

public class FavoriteDAO_24133072
        implements IFavoriteDAO_24133072 {

    @Override
    public long countByVideo(String videoId) {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT COUNT(f) FROM Favorite_24133072 f "
                    + "WHERE f.video.videoId = :videoId",
                    Long.class)
                    .setParameter("videoId", videoId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}