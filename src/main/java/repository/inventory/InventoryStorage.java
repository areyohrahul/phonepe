package repository.inventory;

import model.Inventory;

public interface InventoryStorage {

    Inventory add(Inventory inventory);
    boolean delete(Long id);
    Inventory get(Long id);
    void update(Long id, Inventory inventory);
}
