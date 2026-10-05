package anhtuan.vn.service;

import java.util.List;

import anhtuan.vn.dao.CategoryDAO_24133072;
import anhtuan.vn.dao.ICategoryDAO_24133072;
import anhtuan.vn.entity.Category_24133072;

public class CategoryService_24133072
        implements ICategoryService_24133072 {

    private final ICategoryDAO_24133072 categoryDAO =
            new CategoryDAO_24133072();

    @Override
    public List<Category_24133072> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category_24133072 findById(Integer id) {
        return categoryDAO.findById(id);
    }
}