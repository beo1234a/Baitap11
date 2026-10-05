package anhtuan.vn.service;

import anhtuan.vn.dao.IShareDAO_24133072;
import anhtuan.vn.dao.ShareDAO_24133072;

public class ShareService_24133072
        implements IShareService_24133072 {

    private final IShareDAO_24133072 shareDAO =
            new ShareDAO_24133072();

    @Override
    public long countByVideo(String videoId) {
        return shareDAO.countByVideo(videoId);
    }
}