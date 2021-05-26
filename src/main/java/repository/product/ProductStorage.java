package repository.product;

import model.Product;

import java.util.List;

public interface ProductStorage {

    Product add(Product product);
    boolean delete(Long id);
    Product get(Long id);
    List<Product> getByIds(List<Long> ids);
    List<Product> getAll();
    void update(Long id, Product product);
}
