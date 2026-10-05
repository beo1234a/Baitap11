package anhtuan.vn.dao;

import java.util.List;

import anhtuan.vn.entity.Category_24133072;

public interface ICategoryDAO_24133072 {

    List<Category_24133072> findAll();

    Category_24133072 findById(Integer id);
}