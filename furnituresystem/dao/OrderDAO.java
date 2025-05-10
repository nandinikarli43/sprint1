package dao;

import entity.Order;
import entity.Furniture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class OrderDAO {

    // Add new order
    public void addOrder(Order order) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Fetch furniture to check availability
            Furniture furniture = session.get(Furniture.class, order.getFurniture().getId());

            if (furniture != null && furniture.getQuantity() >= order.getQuantity()) {
                // Reduce quantity
                furniture.reduceQuantity(order.getQuantity());

                // Save order
                order.setTotalPrice(order.getQuantity() * furniture.getPrice());
                order.setOrderDate(new java.util.Date());

                session.save(order);
                session.update(furniture);

                tx.commit();
                System.out.println("Order placed successfully.");
            } else {
                System.out.println("Not enough stock available for " + (furniture != null ? furniture.getName() : "selected furniture"));
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Update order
    public void updateOrder(Order order) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(order);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete order by ID
    public void deleteOrder(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Order order = session.get(Order.class, id);
            if (order != null) {
                session.delete(order);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get order by ID
    public Order getOrderById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all orders
    public List<Order> getAllOrders() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Order", Order.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
