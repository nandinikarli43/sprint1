package dao;

import entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class AdminDAO {

    // Add new admin
    public void addAdmin(Admin admin) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(admin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Update admin details
    public void updateAdmin(Admin admin) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(admin);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Delete admin by ID
    public void deleteAdmin(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            if (admin != null) {
                session.delete(admin);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get admin by ID
    public Admin getAdminById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Admin.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all admins
    public List<Admin> getAllAdmins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Admin", Admin.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

