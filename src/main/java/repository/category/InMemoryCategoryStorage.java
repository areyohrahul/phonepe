package repository.category;

import model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCategoryStorage implements CategoryStorage {

    private final HashMap<Long, Category> data;
    private Long autoId;

    public InMemoryCategoryStorage() {
        data = new HashMap<>();
        autoId = 0L;
    }

    @Override
    public Category add(Category category) {
        synchronized (this) {
            autoId = autoId + 1;
            category.setId(autoId);
            data.put(autoId, category);
            return category;
        }
    }

    @Override
    public boolean delete(Long id) {
        return data.remove(id) != null;
    }

    @Override
    public Category get(Long id) {
        return data.get(id);
    }

    @Override
    public List<Category> getByIds(List<Long> ids) {
        List<Category> categoryList = new ArrayList<>();

        for (Map.Entry<Long, Category> entry: data.entrySet()) {
            if (ids.contains(entry.getKey())) {
                categoryList.add(entry.getValue());
            }
        }

        return categoryList;
    }

    @Override
    public void update(Long id, Category category) {
        data.put(id, category);
    }
}
