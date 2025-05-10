package dao;

import entity.Inventory;
import entity.Furniture;
import java.util.Date; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class InventoryDAO {

    // Add new inventory record
    public void addInventory(Inventory inventory) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(inventory);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Update inventory record
    public void updateInventory(Inventory inventory) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(inventory);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete inventory by ID
    public void deleteInventory(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Inventory inventory = session.get(Inventory.class, id);
            if (inventory != null) {
                session.delete(inventory);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get inventory by ID
    public Inventory getInventoryById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Inventory.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all inventory records
    public List<Inventory> getAllInventory() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Inventory", Inventory.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addInventoryRecord(Furniture furniture, int changeQuantity, String remark) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Create and save new inventory record
            int updatedStock = furniture.getQuantity() + changeQuantity;  // Calculate updated stock quantity
            Inventory inventory = new Inventory(new Date(), changeQuantity, remark, furniture, updatedStock);
            session.save(inventory);

            // Update the furniture quantity
            furniture.setQuantity(updatedStock);
            session.update(furniture);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

}

