package entity;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    // One supplier may supply multiple furniture items
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval ensures that if a Furniture is removed from the list, it gets deleted.
    private List<Furniture> furnitureList = new ArrayList<>(); // Initialize the list to prevent null pointer

    // Constructors
    public Supplier() {}

    public Supplier(String name, String contactNumber, String email, String address) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.furnitureList = new ArrayList<>(); // Initialize the list to ensure it's never null
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    // Optional: toString method for easier debugging
    @Override
    public String toString() {
        return "Supplier [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + 
               ", email=" + email + ", address=" + address + "]";
    }
}
