package strategy.search;

import model.Product;

import java.util.List;

public interface SearchStrategy {

    List<Product> search(String keyword);
}
