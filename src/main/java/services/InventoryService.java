package services;

import dao.CategoryDao;
import dao.InventoryDao;
import dao.ProductDao;
import exceptions.InvalidInventoryIdException;
import exceptions.InvalidCategoryIdException;
import exceptions.InvalidProductIdException;
import model.Category;
import model.Inventory;
import model.Product;
import model.response.CategoryResponse;
import model.response.InventoryResponse;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final InventoryDao inventoryDao;
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public InventoryService(
            final InventoryDao inventoryDao,
            final CategoryDao categoryDao,
            final ProductDao productDao) {
        this.inventoryDao = inventoryDao;
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    public Long createInventory(Inventory inventory) {
        Inventory createdInventory = inventoryDao.createInventory(inventory);
        return createdInventory.getId();
    }

    public Long createCategory(Long inventoryId, Category category) throws InvalidInventoryIdException {
        Inventory inventory = inventoryDao.getById(inventoryId);
        if (inventory == null) {
            throw new InvalidInventoryIdException();
        }
        Category createdCategory = categoryDao.createCategory(category);
        inventory.addCategory(createdCategory.getId());
        inventoryDao.updateById(inventoryId, inventory);

        return createdCategory.getId();
    }

    public Long createProduct(Long categoryId, Product product) throws InvalidCategoryIdException {
        Category category = categoryDao.getById(categoryId);
        if (category == null) {
            throw new InvalidCategoryIdException();
        }
        Product createdProduct = productDao.createProduct(product);
        category.addProduct(createdProduct.getId());
        categoryDao.updateById(categoryId, category);

        return createdProduct.getId();
    }

    public void updateProductName(Long productId, String name) throws InvalidProductIdException {
        Product existingProduct = productDao.getById(productId);
        if (existingProduct == null) {
            throw new InvalidProductIdException();
        }
        existingProduct.setName(name);
        productDao.updateById(productId, existingProduct);
    }

    public void updateCategoryName(Long categoryId, String name) throws InvalidCategoryIdException {
        Category existingCategory = categoryDao.getById(categoryId);
        if (existingCategory == null) {
            throw new InvalidCategoryIdException();
        }
        existingCategory.setName(name);
        categoryDao.updateById(categoryId, existingCategory);
    }

    public void updateInventoryName(Long inventoryId, String name) throws InvalidInventoryIdException {
        Inventory existingInventory = inventoryDao.getById(inventoryId);
        if (existingInventory == null) {
            throw new InvalidInventoryIdException();
        }
        existingInventory.setName(name);
        inventoryDao.updateById(inventoryId, existingInventory);
    }

    public InventoryResponse getInventoryById(Long id) {
        Inventory inventory = inventoryDao.getById(id);
        List<Category> categoryList = categoryDao.getByIds(inventory.getCategoryList());

        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (Category category: categoryList) {
            List<Product> productList = productDao.getByIds(category.getProductList());
            CategoryResponse categoryResponse = CategoryResponse.builder()
                    .id(category.getId())
                    .productList(productList)
                    .build();
            categoryResponseList.add(categoryResponse);
        }

        return InventoryResponse.builder()
                .categoryList(categoryResponseList)
                .id(inventory.getId())
                .name(inventory.getName())
                .build();
    }

    public void deleteProduct(Long categoryId, Long productId) throws InvalidProductIdException {
        boolean isDeleted = productDao.deleteById(productId);
        if (isDeleted) {
            Category category = categoryDao.getById(categoryId);
            category.removeProduct(productId);
        } else {
            throw new InvalidProductIdException();
        }
    }

    public void deleteCategory(Long inventoryId, Long categoryId) throws InvalidCategoryIdException {
        Category category = categoryDao.getById(categoryId);
        if (category == null) {
            throw new InvalidCategoryIdException();
        }
        categoryDao.deleteById(categoryId);
        // Delete products
        for (Long productId: category.getProductList()) {
            try {
                deleteProduct(categoryId, productId);
            } catch (InvalidProductIdException e) {
                e.printStackTrace();
            }
        }
        Inventory inventory = inventoryDao.getById(inventoryId);
        inventory.removeCategory(categoryId);
    }

    public void deleteInventory(Long inventoryId) throws InvalidInventoryIdException {
        Inventory inventory = inventoryDao.getById(inventoryId);
        if (inventory == null) {
            throw new InvalidInventoryIdException();
        }
        inventoryDao.deleteById(inventoryId);
        // Delete categories
        for (Long categoryId: inventory.getCategoryList()) {
            try {
                deleteCategory(inventoryId, categoryId);
            } catch (InvalidCategoryIdException e) {
                e.printStackTrace();
            }
        }
    }
}
