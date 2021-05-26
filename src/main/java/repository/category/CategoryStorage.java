package repository.category;

import model.Category;

import java.util.List;

public interface CategoryStorage {

    Category add(Category category);
    boolean delete(Long id);
    Category get(Long id);
    List<Category> getByIds(List<Long> ids);
    void update(Long id, Category category);
}
