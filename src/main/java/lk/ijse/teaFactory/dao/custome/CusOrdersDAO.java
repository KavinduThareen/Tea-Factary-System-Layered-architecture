package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.dao.SuperDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CusOrdersDAO extends SuperDAO {
    int cusCount() throws SQLException;
   boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException, ClassNotFoundException;
    String generateID() throws SQLException, ClassNotFoundException;
}
