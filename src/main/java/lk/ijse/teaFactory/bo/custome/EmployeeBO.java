package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Employee;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(EmployeeDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Employee search(String id) throws SQLException, ClassNotFoundException;
    public int empCount() throws SQLException;
}
