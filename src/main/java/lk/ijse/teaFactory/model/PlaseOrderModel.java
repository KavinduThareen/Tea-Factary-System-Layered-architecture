package lk.ijse.teaFactory.model;

import lk.ijse.teaFactory.dao.customer.Impl.OrderDetailDAOImpl;
import lk.ijse.teaFactory.dao.customer.OrderDetailDAO;
import lk.ijse.teaFactory.dao.customer.PacketDAO;
import lk.ijse.teaFactory.dao.customer.Impl.PacketDAOImpl;
import lk.ijse.teaFactory.db.DbConnection;
import lk.ijse.teaFactory.dto.PaseOrderDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlaseOrderModel {
    private static CusOrderModel cusModel = new CusOrderModel();
    private static PacketStokeModel packetStokeModel = new PacketStokeModel();
    private static OrderDetailModel orderDetailModel = new OrderDetailModel();
   private static PacketDAO packetDAO = new PacketDAOImpl();
   private static OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();
    public static boolean placeOrder(PaseOrderDto placeOrderDto) throws SQLException {

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

                boolean isOrderSaved = cusModel.saveOrder(orderId,customerId,category,weight,date,description, String.valueOf(payment));
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


