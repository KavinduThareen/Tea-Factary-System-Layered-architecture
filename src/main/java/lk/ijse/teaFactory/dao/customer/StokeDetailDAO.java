package lk.ijse.teaFactory.dao.customer;

import java.sql.Date;
import java.sql.SQLException;

public interface StokeDetailDAO {
    boolean detail(String pid, String lid, Date date) throws SQLException, ClassNotFoundException;
}
