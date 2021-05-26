package dao;

import model.Inventory;
import repository.inventory.InventoryStorage;

public class InventoryDao {

    private InventoryStorage inventoryStorage;

    public InventoryDao(InventoryStorage inventoryStorage) {
        this.inventoryStorage = inventoryStorage;
    }

    public void setStorage(InventoryStorage inventoryStorage) {
        this.inventoryStorage = inventoryStorage;
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryStorage.add(inventory);
    }

    public Inventory getById(Long id) {
        return inventoryStorage.get(id);
    }

    public void updateById(Long id, Inventory inventory) {
        inventoryStorage.update(id, inventory);
    }

    public boolean deleteById(Long id) {
        return inventoryStorage.delete(id);
    }
}
