package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.dao.SuperDAO;

import java.sql.Date;
import java.sql.SQLException;

public interface StokeDetailDAO extends SuperDAO {
    boolean detail(String pid, String lid, Date date) throws SQLException, ClassNotFoundException;
}
