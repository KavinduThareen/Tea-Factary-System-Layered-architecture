package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.Date;
import java.sql.SQLException;

public interface SupplingDetailDAO {
    boolean detail(String sid, String id, Date sDate) throws SQLException, ClassNotFoundException;
}
