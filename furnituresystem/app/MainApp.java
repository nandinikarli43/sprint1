package app;

import entity.Admin;
import entity.Customer;
import service.AdminService;
import service.CustomerService;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();

        while (true) {
            System.out.println("\n--- Furniture Business Management System ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. New Customer Registration");
            System.out.println("4. New Admin Registration"); // Option to create new admin
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Admin Login
                    System.out.print("Enter Admin Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String password = scanner.nextLine();
                    Admin admin = adminService.login(username, password);
                    if (admin != null) {
                        System.out.println("Welcome, Admin " + admin.getUsername());
                        adminService.adminDashboard(scanner);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 2:
                    // Customer Login
                    System.out.print("Enter your Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter your Password: ");
                    String customerPassword = scanner.nextLine();

                    Customer customer = customerService.loginCustomer(email, customerPassword);
                    if (customer != null) {
                        System.out.println("Welcome, " + customer.getName());
                        customerService.customerDashboard(scanner, customer);
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 3:
                    // New Customer Registration
                    System.out.print("Enter your Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter your Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter your Password: ");
                    String newPassword = scanner.nextLine();
                    Customer newCustomer = new Customer(name, newEmail, newPassword);
                    customerService.addCustomer(newCustomer);
                    System.out.println("Registration successful! Your Customer ID is: " + newCustomer.getId());
                    break;

                case 4:
                    // New Admin Registration
                    System.out.print("Enter New Admin Username: ");
                    String newAdminUsername = scanner.nextLine();
                    System.out.print("Enter New Admin Email: ");
                    String newAdminEmail = scanner.nextLine();
                    System.out.print("Enter New Admin Password: ");
                    String newAdminPassword = scanner.nextLine();

                    Admin newAdmin = new Admin(newAdminUsername, newAdminEmail, newAdminPassword);
                    adminService.addAdmin(newAdmin);
                    System.out.println("New Admin registered successfully!");
                    break;

                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select again.");
            }
        }
    }
}
