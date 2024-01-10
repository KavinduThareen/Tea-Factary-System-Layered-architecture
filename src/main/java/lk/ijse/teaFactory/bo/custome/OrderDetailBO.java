package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
    public boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    public int ordersCount() throws SQLException, ClassNotFoundException;
}
