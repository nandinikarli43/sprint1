package service;

import entity.Admin;

import entity.Furniture;
import entity.Supplier;
import entity.Inventory;  // Make sure to import Inventory entity
import service.InventoryService;  // Import InventoryService
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class AdminService {

    private InventoryService inventoryService = new InventoryService();  // Initialize inventoryService

    public void addAdmin(Admin admin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(admin);
        tx.commit();
        session.close();
    }

    public Admin login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Admin> query = session.createQuery("FROM Admin WHERE username = :username AND password = :password", Admin.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        Admin admin = query.uniqueResult();
        session.close();
        return admin;
    }

    public void adminDashboard(Scanner scanner) {
    	FurnitureService furnitureService = new FurnitureService();
        SupplierService supplierService = new SupplierService();
        CustomerService customerService = new CustomerService();
        
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Add Furniture");
            System.out.println("2. View Furniture");
            System.out.println("3. Add Supplier");
            System.out.println("4. View Suppliers");
            System.out.println("5. View Inventory");
            System.out.println("6. View Customer Details");
            System.out.println("7. Add Inventory Record");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            //FurnitureService furnitureService = new FurnitureService();
            //SupplierService supplierService = new SupplierService();
            //CustomerService customerService = new CustomerService();

            switch (choice) {
                case 1:
                    // Add Furniture
                    System.out.print("Enter Furniture Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Furniture Type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    // Show available suppliers
                    List<Supplier> suppliers = supplierService.getAllSuppliers();
                    if (suppliers.isEmpty()) {
                        System.out.println("No suppliers available. Please add a supplier first.");
                        break;
                    }
                    System.out.println("Available Suppliers:");
                    for (Supplier s : suppliers) {
                        System.out.println("ID: " + s.getId() + ", Name: " + s.getName());
                    }
                    System.out.print("Enter Supplier ID to assign: ");
                    int supplierId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    Supplier selectedSupplier = supplierService.getSupplierById(supplierId);
                    if (selectedSupplier == null) {
                        System.out.println("Invalid supplier ID.");
                        break;
                    }

                    Furniture furniture = new Furniture(name, type, price, qty, selectedSupplier);
                    furnitureService.addFurniture(furniture);
                    System.out.println("Furniture added.");
                    break;

                case 2:
                    // View Furniture
                    List<Furniture> furnitureList = furnitureService.getAllFurniture();
                    for (Furniture f : furnitureList) {
                        System.out.println(f);
                        System.out.println();
                    }
         
                    break;

                case 3:
                    // Add Supplier
                    System.out.print("Enter Supplier Name: ");
                    String sName = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    Supplier supplier = new Supplier(sName, contact, email, address);
                    supplierService.addSupplier(supplier);
                    System.out.println("Supplier added.");
                    break;

                case 4:
                    // View Suppliers
                    suppliers = supplierService.getAllSuppliers(); // ✅ Just reuse the existing variable
                    for (Supplier s : suppliers) {
                        System.out.println(s);
                    }
                    break;

                case 5:
                    // View Inventory
                    List<Inventory> inventories = inventoryService.getAllInventory();  // Correct usage of inventoryService
                    System.out.println("\n--- Inventory Records ---");
                    if (inventories != null && !inventories.isEmpty()) {
                        for (Inventory inventory : inventories) {
                            System.out.println("Furniture: " + inventory.getFurniture().getName() +
                                               ", Date: " + inventory.getStockDate() +
                                               ", Quantity Change: " + inventory.getChangeQuantity() +
                                               ", Remark: " + inventory.getRemark());
                        }
                    } else {
                        System.out.println("No inventory records found.");
                    }
                    break;

                case 6:
                    // View Customer Details
                    customerService.viewAllCustomers();
                    break;
                    
                case 7:
                    // Add Inventory Record
                    List<Furniture> allFurniture = furnitureService.getAllFurniture();
                    if (allFurniture.isEmpty()) {
                        System.out.println("No furniture found. Add furniture first.");
                        break;
                    }

                    System.out.println("Available Furniture:");
                    for (Furniture f : allFurniture) {
                        System.out.println("ID: " + f.getId() + ", Name: " + f.getName());
                    }

                    System.out.print("Enter Furniture ID to update inventory: ");
                    int furnitureId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    Furniture selectedFurniture = furnitureService.getFurnitureById(furnitureId);
                    if (selectedFurniture == null) {
                        System.out.println("Invalid furniture ID.");
                        break;
                    }

                    System.out.print("Enter quantity change (positive for stock-in, negative for stock-out): ");
                    int changeQty = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter remark (e.g., Restocked, Sold, Damaged): ");
                    String remark = scanner.nextLine();

                    inventoryService.addInventoryRecord(selectedFurniture, changeQty, remark);
                    System.out.println("Inventory record added.");
                    
                    System.out.println("Updated Furniture Quantity: " + selectedFurniture.getQuantity());
                    break;

                case 8:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
