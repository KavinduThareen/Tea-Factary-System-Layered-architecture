package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Supplier;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    public ArrayList<SupplierDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(SupplierDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(SupplierDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Supplier search(String id) throws SQLException, ClassNotFoundException;
}
