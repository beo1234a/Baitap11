package anhtuan.vn.service;

import java.util.List;

import anhtuan.vn.dao.IVideoDAO_24133072;
import anhtuan.vn.dao.VideoDAO_24133072;
import anhtuan.vn.entity.Video_24133072;

public class VideoService_24133072
        implements IVideoService_24133072 {

    private final IVideoDAO_24133072 videoDAO =
            new VideoDAO_24133072();

    @Override
    public List<Video_24133072> findAll() {
        return videoDAO.findAll();
    }

    @Override
    public Video_24133072 findById(
            String id) {

        return videoDAO.findById(id);
    }

    @Override
    public void insert(
            Video_24133072 video) {

        videoDAO.insert(video);
    }

    @Override
    public void update(
            Video_24133072 video) {

        videoDAO.update(video);
    }

    @Override
    public void delete(
            String id) {

        videoDAO.delete(id);
    }

    @Override
    public List<Video_24133072> findPage(
            int page,
            int pageSize) {

        if (page < 1) {
            page = 1;
        }

        return videoDAO.findPage(
                page,
                pageSize);
    }

    @Override
    public long count() {
        return videoDAO.count();
    }

    @Override
    public int totalPages(
            int pageSize) {

        long totalVideos =
                videoDAO.count();

        return (int) Math.ceil(
                (double) totalVideos
                / pageSize);
    }

    @Override
    public List<Video_24133072> findByCategoryPage(
            Integer categoryId,
            int page,
            int pageSize) {

        if (page < 1) {
            page = 1;
        }

        return videoDAO.findByCategoryPage(
                categoryId,
                page,
                pageSize);
    }

    @Override
    public long countByCategory(
            Integer categoryId) {

        return videoDAO.countByCategory(
                categoryId);
    }

    @Override
    public int totalPagesByCategory(
            Integer categoryId,
            int pageSize) {

        long totalVideos =
                videoDAO.countByCategory(
                        categoryId);

        return (int) Math.ceil(
                (double) totalVideos
                / pageSize);
    }

    @Override
    public String generateVideoId() {
        return videoDAO.generateVideoId();
    }
}