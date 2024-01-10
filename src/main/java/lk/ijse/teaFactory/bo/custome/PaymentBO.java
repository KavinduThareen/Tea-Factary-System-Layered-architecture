package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Salary;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<SalaryDto> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(SalaryDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(SalaryDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public String generateID() throws SQLException, ClassNotFoundException;
    public Salary search(String id) throws SQLException, ClassNotFoundException;
}
