package service;

import dao.SupplierDAO;
import entity.Supplier;

import java.util.List;

public class SupplierService {
    private SupplierDAO supplierDAO = new SupplierDAO();

    public void addSupplier(Supplier supplier) {
        supplierDAO.addSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    public Supplier getSupplierById(int id) {
        return supplierDAO.getSupplierById(id);
    }

    public void updateSupplier(Supplier supplier) {
        supplierDAO.updateSupplier(supplier);
    }

    public void deleteSupplier(int id) {
        supplierDAO.deleteSupplier(id);
    }
}

