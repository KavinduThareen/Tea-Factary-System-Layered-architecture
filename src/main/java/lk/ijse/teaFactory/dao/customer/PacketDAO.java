package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dto.PacketStokeDto;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface PacketDAO extends CrudDAO<PacketStokeDto> {
    boolean updateItem(List<CartTm> cartTmList) throws SQLException;
}