package anhtuan.vn.dao;

import javax.persistence.EntityManager;

import anhtuan.vn.config.JPAConfig_24133072;

public class ShareDAO_24133072 implements IShareDAO_24133072 {

    @Override
    public long countByVideo(String videoId) {
        EntityManager em = JPAConfig_24133072.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT COUNT(s) FROM Share_24133072 s "
                    + "WHERE s.video.videoId = :videoId",
                    Long.class)
                    .setParameter("videoId", videoId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}