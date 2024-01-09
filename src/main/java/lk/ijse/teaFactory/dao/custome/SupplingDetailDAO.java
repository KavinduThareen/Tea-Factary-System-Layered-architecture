package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.dao.SuperDAO;

import java.sql.Date;
import java.sql.SQLException;

public interface SupplingDetailDAO extends SuperDAO {
    boolean detail(String sid, String id, Date sDate) throws SQLException, ClassNotFoundException;
}
