package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrdersOB extends SuperBO {
    public String generateID() throws SQLException, ClassNotFoundException;
    public int cusCount() throws SQLException;
    public boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException, ClassNotFoundException;
}
