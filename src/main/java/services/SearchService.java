package services;

import model.Product;
import strategy.search.SearchStrategy;

import java.util.List;

public class SearchService {

    private SearchStrategy searchStrategy;

    public SearchService(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Product> search(String keyword) {
        return searchStrategy.search(keyword);
    }
}
