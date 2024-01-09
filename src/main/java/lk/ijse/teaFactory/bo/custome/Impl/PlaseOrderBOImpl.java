package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.bo.custome.PlaseOrderBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.CusOrdersDAO;
import lk.ijse.teaFactory.dao.custome.OrderDetailDAO;
import lk.ijse.teaFactory.dao.custome.PacketDAO;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.PaseOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.*;

public class PlaseOrderBOImpl implements PlaseOrderBO {

    private static PacketDAO packetDAO = (PacketDAO) DAOFactory.getDaoFactory().getDAO(PACKETSTOKE);
    private static OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(ORDERDETAIL);
    private static CusOrdersDAO cusOrdersDAO = (CusOrdersDAO) DAOFactory.getDaoFactory().getDAO(CUSORDERS);
    @Override
    public boolean placeOrder(PaseOrderDto placeOrderDto) throws SQLException {

        String orderId = placeOrderDto.getid();
        String customerId = placeOrderDto.getCId();
        String category = placeOrderDto.getCatagary();
        double weight = placeOrderDto.getWeigth();
        LocalDate date = placeOrderDto.getDate();
        String description = placeOrderDto.getDescreption();
        double payment = placeOrderDto.getPayment();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            //  var dto1 = new CusOrderDto(orderId,customerId,category,weight,date,description, String.valueOf(payment));
            boolean isOrderSaved = cusOrdersDAO.saveOrder(orderId,customerId,category,weight,date,description, String.valueOf(payment));
            //  boolean isOrderSaved = cusOrdersDAO.save(dto1);
            if (isOrderSaved) {
                //   boolean isUpdated = packetStokeModel.updateItem(placeOrderDto.getCartTmList());
                boolean isUpdated = packetDAO.updateItem(placeOrderDto.getCartTmList());

                if (isUpdated  ) {
                    boolean isOrderDetailSaved = orderDetailDAO.saveOrderDetails(placeOrderDto.getid(), placeOrderDto.getCartTmList());


                    if (isOrderDetailSaved) {
                        connection.commit();
                    }
                }
            }
            connection.rollback();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return true;

    }
}
