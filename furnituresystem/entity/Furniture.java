package entity;

import javax.persistence.*;

@Entity
@Table(name = "furniture")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    // Add supplier relationship
    @ManyToOne
    @JoinColumn(name = "supplier_id")  // This assumes 'supplier_id' is the FK column in your 'furniture' table
    private Supplier supplier;

    // Constructors
    public Furniture() {}

    public Furniture(String name, String type, double price, int quantity, Supplier supplier) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    // Getters and Setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    // Optional: toString method for easier debugging
    @Override
    public String toString() {
        return "Furniture [id=" + id +
               ", name=" + name +
               ", type=" + type +
               ", price=" + price +
               ", quantity=" + quantity +
               ", supplier=" + (supplier != null ? supplier.getName() : "N/A") +
               "]";
    }

    // Add the reduceQuantity method
    public void reduceQuantity(int quantityToReduce) {
        if (this.quantity >= quantityToReduce) {
            this.quantity -= quantityToReduce;
        } else {
            throw new IllegalStateException("Not enough stock available");
        }
    }
}
