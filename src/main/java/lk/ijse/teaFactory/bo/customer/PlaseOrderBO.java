package lk.ijse.teaFactory.bo.customer;

import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dao.SuperDAO;
import lk.ijse.teaFactory.dao.customer.SalaryDAO;
import lk.ijse.teaFactory.dto.PaseOrderDto;

import java.sql.SQLException;

public interface PlaseOrderBO extends SuperBO {
    boolean placeOrder(PaseOrderDto placeOrderDto) throws SQLException;
}
