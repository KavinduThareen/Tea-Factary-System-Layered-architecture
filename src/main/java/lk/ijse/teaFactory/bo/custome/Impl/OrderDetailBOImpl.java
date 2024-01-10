package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.OrderDetailBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.OrderDetailDAO;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDERDETAIL);
    @Override
    public boolean saveOrderDetails(String orderId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.saveOrderDetails(orderId,cartTmList);
    }
    @Override
    public int ordersCount() throws SQLException, ClassNotFoundException {
        return orderDetailDAO.ordersCount();
    }
}
