package strategy.search;

import dao.ProductDao;
import model.Product;

import java.util.List;

public class BasicSearchStrategy implements SearchStrategy {

    private final ProductDao productDao;

    public BasicSearchStrategy(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> search(String keyword) {
        List<Product> productList = productDao.getAllProducts();
        productList.removeIf(product -> !product.getName().toLowerCase().contains(keyword.toLowerCase()));

        return productList;
    }
}
