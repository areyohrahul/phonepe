import dao.CategoryDao;
import dao.InventoryDao;
import dao.ProductDao;
import exceptions.InvalidCategoryIdException;
import exceptions.InvalidInventoryIdException;
import model.Category;
import model.Inventory;
import model.Product;
import repository.category.CategoryStorage;
import repository.category.InMemoryCategoryStorage;
import repository.inventory.InMemoryInventoryStorage;
import repository.inventory.InventoryStorage;
import repository.product.InMemoryProductStorage;
import repository.product.ProductStorage;
import services.InventoryService;
import services.SearchService;
import strategy.search.BasicSearchStrategy;
import strategy.search.SearchStrategy;

public class Driver {

    public static void main(String[] args) {
        InventoryStorage inventoryStorage = new InMemoryInventoryStorage();
        InventoryDao inventoryDao = new InventoryDao(inventoryStorage);

        CategoryStorage categoryStorage = new InMemoryCategoryStorage();
        CategoryDao categoryDao = new CategoryDao(categoryStorage);

        ProductStorage productStorage = new InMemoryProductStorage();
        ProductDao productDao = new ProductDao(productStorage);

        SearchStrategy searchStrategy = new BasicSearchStrategy(productDao);
        SearchService searchService = new SearchService(searchStrategy);

        InventoryService inventoryService = new InventoryService(inventoryDao, categoryDao, productDao);

        Inventory inventory = new Inventory();
        inventory.setName("Basic Inventory");
        Long inventoryId = inventoryService.createInventory(inventory);

        Category category = new Category();
        category.setName("Mobile");
        Long categoryId = 0L;
        try {
            categoryId = inventoryService.createCategory(inventoryId, category);
        } catch (InvalidInventoryIdException e) {
            e.printStackTrace();
        }

        Product product1 = new Product();
        product1.setName("Redmi Note 8 (Neptune Blue, 4GB RAM, 64GB Storage)");
        try {
            inventoryService.createProduct(categoryId, product1);
        } catch (InvalidCategoryIdException e) {
            e.printStackTrace();
        }

        Product product2 = new Product();
        product2.setName("Red Mobile Cases");
        try {
            inventoryService.createProduct(categoryId, product2);
        } catch (InvalidCategoryIdException e) {
            e.printStackTrace();
        }

        System.out.println(inventoryService.getInventoryById(inventoryId));
        System.out.println(searchService.search("Red"));
        System.out.println(searchService.search("Note"));
    }
}
