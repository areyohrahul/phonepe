package repository.inventory;

import model.Inventory;

import java.util.HashMap;

public class InMemoryInventoryStorage implements InventoryStorage {

    private final HashMap<Long, Inventory> data;
    private Long autoId;

    public InMemoryInventoryStorage() {
        data = new HashMap<>();
        autoId = 0L;
    }

    @Override
    public Inventory add(Inventory inventory) {
        synchronized (this) {
            autoId = autoId + 1;
            inventory.setId(autoId);
            data.put(autoId, inventory);
            return inventory;
        }
    }

    @Override
    public boolean delete(Long id) {
        return data.remove(id) != null;
    }

    @Override
    public Inventory get(Long id) {
        return data.get(id);
    }

    @Override
    public void update(Long id, Inventory inventory) {
        data.put(id, inventory);
    }
}
