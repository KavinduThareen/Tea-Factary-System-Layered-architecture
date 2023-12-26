package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException, ClassNotFoundException;
    int ordersCount() throws SQLException, ClassNotFoundException;
}
