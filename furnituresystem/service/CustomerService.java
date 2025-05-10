package service;

import dao.CustomerDAO;

import dao.FurnitureDAO;
import dao.OrderDAO;
import entity.Customer;
import entity.Furniture;
import entity.Order;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CustomerService {

    private CustomerDAO customerDAO;
    private OrderDAO orderDAO;

    public CustomerService() {
        customerDAO = new CustomerDAO();  // Initialize DAO
        orderDAO = new OrderDAO();
    }

    // Add new customer
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    // Update existing customer
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    // Delete customer by ID
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }

    // Get customer by ID
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    // Place an order
    public void placeOrder(Order order) {
        orderDAO.addOrder(order);
    }

    // Get all orders of a customer
    public List<Order> getCustomerOrders(int customerId) {
        return customerDAO.getOrdersByCustomer(customerId);
    }

    // View all customers (for Admin)
    public void viewAllCustomers() {
        List<Customer> customers = getAllCustomers();
        System.out.println("\n--- Customer List ---");
        for (Customer c : customers) {
            System.out.println("ID: " + c.getId() + ", Name: " + c.getName() + ", Email: " + c.getEmail());
        }
    }

    // Login Customer by email and password
    public Customer loginCustomer(String email, String password) {
        List<Customer> customers = getAllCustomers();
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                return c;  // return customer if credentials match
            }
        }
        return null;  // return null if no matching customer found
    }

    // Customer Dashboard
    public void customerDashboard(Scanner scanner, Customer customer) {
        FurnitureDAO furnitureDAO = new FurnitureDAO();  // To access furniture list
        while (true) {
            System.out.println("\n--- Customer Dashboard ---");
            System.out.println("1. View Available Furniture");
            System.out.println("2. Place an Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // View all furniture
                    List<Furniture> furnitureList = furnitureDAO.getAllFurniture();
                    System.out.println("\n--- Available Furniture ---");
                    for (Furniture f : furnitureList) {
                    	System.out.println("ID: " + f.getId() + ", Name: " + f.getName() +
                    	        ", Price: " + f.getPrice() + ", Type: " + f.getType() +
                    	        ", Available Qty: " + f.getQuantity());

                    }
                    break;

                case 2:
                    // Place an order
                    System.out.print("Enter Furniture ID to Order: ");
                    int furnitureId = scanner.nextInt();
                    scanner.nextLine();
                    Furniture selected = furnitureDAO.getFurnitureById(furnitureId);
                    if (selected != null) {
                        System.out.print("Enter Quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();

                        if (selected.getQuantity() >= quantity) {
                            Order order = new Order();
                            order.setCustomer(customer);
                            order.setFurniture(selected);
                            order.setQuantity(quantity);
                            order.setOrderDate(new Date());

                            order.setTotalPrice(selected.getPrice() * quantity); // Optional

                            placeOrder(order);  // orderDAO.addOrder() will reduce quantity
                            System.out.println("Order placed successfully!");
                        } else {
                            System.out.println("Sorry, only " + selected.getQuantity() + " item(s) available. Order not placed.");
                        }

                    } else {
                        System.out.println("Invalid Furniture ID.");
                    }
                    break;

                case 3:
                    // View all orders of customer
                    List<Order> orders = getCustomerOrders(customer.getId());
                    System.out.println("\n--- Your Orders ---");
                    for (Order o : orders) {
                        System.out.println("Order ID: " + o.getId() + ", Furniture: " + o.getFurniture().getName() +
                                ", Quantity: " + o.getQuantity() + ", Total Price: " + o.getTotalPrice() + ", Date: " + o.getOrderDate());
                    }
                    break;

                case 4:
                    // Logout
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}