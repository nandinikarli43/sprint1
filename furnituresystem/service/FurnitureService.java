package service;

import dao.FurnitureDAO;
import dao.InventoryDAO;
import entity.Furniture;
import entity.Inventory;

import java.util.List;

public class FurnitureService {

    private FurnitureDAO furnitureDAO;
    private InventoryDAO inventoryDAO; // ✅ Add this

    public FurnitureService() {
        furnitureDAO = new FurnitureDAO();
        inventoryDAO = new InventoryDAO(); // ✅ Initialize it
    }

    // Add new furniture
    public void addFurniture(Furniture furniture) {
        furnitureDAO.addFurniture(furniture);
    }

    // Update furniture
    public void updateFurniture(Furniture furniture) {
        furnitureDAO.updateFurniture(furniture);
    }

    // Delete furniture by ID
    public void deleteFurniture(int id) {
        furnitureDAO.deleteFurniture(id);
    }

    // Get furniture by ID
    public Furniture getFurnitureById(int id) {
        return furnitureDAO.getFurnitureById(id);
    }

    // Get all furniture
    public List<Furniture> getAllFurniture() {
        return furnitureDAO.getAllFurniture();
    }

    // ✅ Add this method to fix the error in AdminService
    public void displayInventory() {
        List<Inventory> inventoryList = inventoryDAO.getAllInventory();
        System.out.println("\n--- Inventory Records ---");
        for (Inventory inv : inventoryList) {
            System.out.println("ID: " + inv.getId()
                    + ", Furniture: " + inv.getFurniture().getName()
                    + ", Qty Change: " + inv.getChangeQuantity()
                    + ", Date: " + inv.getStockDate()
                    + ", Remark: " + inv.getRemark());
        }
    }
 // Optional: Add inventory record
    public void addInventoryRecord(Inventory inventory) {
        inventoryDAO.addInventory(inventory);
    }

}
