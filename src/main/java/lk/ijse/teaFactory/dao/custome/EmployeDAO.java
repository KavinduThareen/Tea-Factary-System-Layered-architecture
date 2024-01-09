package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Employee;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface EmployeDAO extends CrudDAO<Employee> {
    public int empCount() throws SQLException;
}
