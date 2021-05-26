package repository.product;

import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryProductStorage implements ProductStorage {

    private final HashMap<Long, Product> data;
    private Long autoId;

    public InMemoryProductStorage() {
        data = new HashMap<>();
        autoId = 0L;
    }

    @Override
    public Product add(Product product) {
        synchronized (this) {
            autoId = autoId + 1;
            product.setId(autoId);
            data.put(autoId, product);
            return product;
        }
    }

    @Override
    public boolean delete(Long id) {
        return data.remove(id) != null;
    }

    @Override
    public Product get(Long id) {
        return data.get(id);
    }

    @Override
    public List<Product> getByIds(List<Long> ids) {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Long, Product> entry: data.entrySet()) {
            if (ids.contains(entry.getKey())) {
                productList.add(entry.getValue());
            }
        }

        return productList;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Long, Product> entry: data.entrySet()) {
            productList.add(entry.getValue());
        }

        return productList;
    }

    @Override
    public void update(Long id, Product product) {
        data.put(id, product);
    }
}
