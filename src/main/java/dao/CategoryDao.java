package dao;

import model.Category;
import repository.category.CategoryStorage;

import java.util.List;

public class CategoryDao {

    private CategoryStorage categoryStorage;

    public CategoryDao(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    public void setStorage(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    public Category createCategory(Category category) {
        return categoryStorage.add(category);
    }

    public Category getById(Long id) {
        return categoryStorage.get(id);
    }

    public void updateById(Long id, Category category) {
        categoryStorage.update(id, category);
    }

    public boolean deleteById(Long id) {
        return categoryStorage.delete(id);
    }

    public List<Category> getByIds(List<Long> ids) {
        return categoryStorage.getByIds(ids);
    }
}
