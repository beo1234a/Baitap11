package anhtuan.vn.dao;

import java.util.List;

import anhtuan.vn.entity.Video_24133072;

public interface IVideoDAO_24133072 {

    List<Video_24133072> findAll();

    Video_24133072 findById(String id);

    void insert(Video_24133072 video);

    void update(Video_24133072 video);

    void delete(String id);

    List<Video_24133072> findPage(
            int page,
            int pageSize);

    long count();

    List<Video_24133072> findByCategoryPage(
            Integer categoryId,
            int page,
            int pageSize);

    long countByCategory(Integer categoryId);

    String generateVideoId();
}