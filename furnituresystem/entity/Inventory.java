package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stock_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stockDate;

    @Column(name = "change_quantity")
    private int changeQuantity;  // +ve for add, -ve for remove

    @Column(name = "remark")
    private String remark;

    @Column(name = "stock_quantity")  // Added this field for current stock quantity
    private int stockQuantity;  // Field to store current stock quantity

    // Relationship: inventory is tied to a furniture item
    @ManyToOne
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;

    // Constructors
    public Inventory() {}

    public Inventory(Date stockDate, int changeQuantity, String remark, Furniture furniture, int stockQuantity) {
        this.stockDate = stockDate;
        this.changeQuantity = changeQuantity;
        this.remark = remark;
        this.furniture = furniture;
        this.stockQuantity = stockQuantity;  // Set stock quantity
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStockDate() {
        return stockDate;
    }

    public void setStockDate(Date stockDate) {
        this.stockDate = stockDate;
    }

    public int getChangeQuantity() {
        return changeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        this.changeQuantity = changeQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", date=" + stockDate + ", quantity=" + changeQuantity +
               ", remark=" + remark + ", furniture=" + (furniture != null ? furniture.getName() : "N/A") +
               ", stock_quantity=" + stockQuantity + "]";
    }
}
