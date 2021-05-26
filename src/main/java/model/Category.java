package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {

    private Long id;
    private String name;
    private List<Long> productList;

    public Category() {
        this.productList = new ArrayList<>();
    }

    public void addProduct(Long productId) {
        productList.add(productId);
    }

    public void removeProduct(Long productId) {
        productList.remove(productId);
    }
}
