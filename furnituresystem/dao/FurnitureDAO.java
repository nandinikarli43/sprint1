package dao;

import entity.Furniture;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class FurnitureDAO {

    // Add new furniture
	public int addFurniture(Furniture furniture) {
	    Transaction tx = null;
	    Integer id = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        tx = session.beginTransaction();
	        id = (Integer) session.save(furniture);
	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) tx.rollback();
	        e.printStackTrace();
	    }
	    return id;
	}


    // Update furniture
    public void updateFurniture(Furniture furniture) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(furniture);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete furniture
    public boolean deleteFurniture(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Furniture furniture = session.get(Furniture.class, id);
            if (furniture != null) {
                session.delete(furniture);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }


    // Get furniture by ID
    public Furniture getFurnitureById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Furniture.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all furniture
    public List<Furniture> getAllFurniture() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Furniture", Furniture.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get furniture by name (optional method)
    public List<Furniture> getFurnitureByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Furniture where name = :name", Furniture.class)
                          .setParameter("name", name)
                          .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
