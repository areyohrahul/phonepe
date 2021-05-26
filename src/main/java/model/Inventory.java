package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Inventory {

    private Long id;
    private String name;
    private List<Long> categoryList;

    public Inventory() {
        categoryList = new ArrayList<>();
    }

    public void addCategory(Long categoryId) {
        categoryList.add(categoryId);
    }

    public void removeCategory(Long categoryId) {
        categoryList.remove(categoryId);
    }
}
