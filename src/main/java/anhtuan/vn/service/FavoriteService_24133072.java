package anhtuan.vn.service;

import anhtuan.vn.dao.FavoriteDAO_24133072;
import anhtuan.vn.dao.IFavoriteDAO_24133072;

public class FavoriteService_24133072
        implements IFavoriteService_24133072 {

    private final IFavoriteDAO_24133072 favoriteDAO =
            new FavoriteDAO_24133072();

    @Override
    public long countByVideo(String videoId) {
        return favoriteDAO.countByVideo(videoId);
    }
}