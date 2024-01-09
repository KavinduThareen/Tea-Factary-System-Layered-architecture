package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Customer;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
    public int cusCount() throws SQLException, ClassNotFoundException;

}
