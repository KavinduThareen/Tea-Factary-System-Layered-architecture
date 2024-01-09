package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    public ArrayList<CustomerDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(CustomerDto dto ) throws SQLException, ClassNotFoundException;
    public boolean update(CustomerDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Customer search(String id) throws SQLException, ClassNotFoundException;
 //   public  List<CustomerDto> loadAllItems() throws SQLException, ClassNotFoundException;

}
