package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.OrderDetailDAO;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            if(!saveOrderDetails(orderId, tm)) {
                return false;
            }
        }
        return true;
    }
    public boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO order_detailse VALUES(?, ?, ?, ?)",orderId,tm.getItemId(),tm.getWeigth(),tm.getPayment());
    }

    @Override
    public int ordersCount() throws SQLException, ClassNotFoundException {
        int rowCount = 0;
        ResultSet resultSet = SQLUtil.execute( "SELECT SUM(weigth) AS total_weight FROM order_detailse");

            if (resultSet.next()) {
                rowCount = resultSet.getInt("total_weight");
                System.out.println("Number of rows: " + rowCount);
            }

        return rowCount;
    }

}
