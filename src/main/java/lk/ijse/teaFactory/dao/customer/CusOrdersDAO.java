package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dto.CusOrderDto;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CusOrdersDAO {
    int cusCount() throws SQLException;
   boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException, ClassNotFoundException;
    String generateID() throws SQLException, ClassNotFoundException;
}
