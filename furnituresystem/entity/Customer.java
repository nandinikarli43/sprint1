package entity;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false) // Add password column in the table
    private String password; // Add password field

    // Default constructor
    public Customer() {}

    // Constructor with parameters (including password)
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password; // Initialize password
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {  // Add getter for password
        return password;
    }

    public void setPassword(String password) { // Add setter for password
        this.password = password;
    }
}
