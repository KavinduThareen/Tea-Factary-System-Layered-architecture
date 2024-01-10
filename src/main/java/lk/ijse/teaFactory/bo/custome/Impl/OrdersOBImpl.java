package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.OrdersOB;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.CusOrdersDAO;

import java.sql.SQLException;
import java.time.LocalDate;

public class OrdersOBImpl implements OrdersOB {
    CusOrdersDAO cusOrdersDAO = (CusOrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSORDERS);
    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        return cusOrdersDAO.generateID();
    }

    @Override
    public int cusCount() throws SQLException {
        return cusOrdersDAO.cusCount();
    }

    @Override
    public boolean saveOrder(String orderId, String customerId, String catagary, double weigth, LocalDate date, String descreption, String payment) throws SQLException, ClassNotFoundException {
        return cusOrdersDAO.saveOrder(orderId,customerId,catagary,weigth,date,descreption,payment);
    }
}
