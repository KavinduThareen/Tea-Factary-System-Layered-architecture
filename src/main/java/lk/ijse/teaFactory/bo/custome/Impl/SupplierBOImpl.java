package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.Entity.Salary;
import lk.ijse.teaFactory.Entity.Supplier;
import lk.ijse.teaFactory.bo.custome.SupplierBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.SupplierDAO;
import lk.ijse.teaFactory.dto.CustomerDto;
import lk.ijse.teaFactory.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.SUPPLIER;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(SUPPLIER);
    @Override
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers=supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDtos=new ArrayList<>();
        for (Supplier supplier:suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getId(),supplier.getName(),supplier.getAddress(),supplier.getContac()));
        }
        return supplierDtos;
    }

    @Override
    public boolean save(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getContac()));
    }

    @Override
    public boolean update(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(),dto.getName(),dto.getAddress(),dto.getContac()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateID();
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(id);
    }
}
