package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.PacketStoke;
import lk.ijse.teaFactory.dao.CrudDAO;
import lk.ijse.teaFactory.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface PacketDAO extends CrudDAO<PacketStoke> {
    boolean updateItem(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    public int stokeCount() throws SQLException, ClassNotFoundException;
}
