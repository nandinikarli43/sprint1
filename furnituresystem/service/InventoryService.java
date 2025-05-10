package service;

import dao.InventoryDAO;
import entity.Furniture;
import entity.Inventory;

import java.util.List;

public class InventoryService {

    private InventoryDAO inventoryDAO = new InventoryDAO();

    public void addInventory(Inventory inventory) {
        inventoryDAO.addInventory(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryDAO.getAllInventory();
    }
    
    public void addInventoryRecord(Furniture furniture, int changeQuantity, String remark) {
        inventoryDAO.addInventoryRecord(furniture, changeQuantity, remark);
    }
}
