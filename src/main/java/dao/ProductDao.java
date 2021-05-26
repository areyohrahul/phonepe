package dao;

import model.Product;
import repository.product.ProductStorage;

import java.util.List;

public class ProductDao {

    private ProductStorage productStorage;

    public ProductDao(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public void setStorage(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public Product createProduct(Product product) {
        return productStorage.add(product);
    }

    public Product getById(Long id) {
        return productStorage.get(id);
    }

    public List<Product> getAllProducts() {
        return productStorage.getAll();
    }

    public void updateById(Long id, Product product) {
        productStorage.update(id, product);
    }

    public boolean deleteById(Long id) {
        return productStorage.delete(id);
    }

    public List<Product> getByIds(List<Long> ids) {
        return productStorage.getByIds(ids);
    }
}
