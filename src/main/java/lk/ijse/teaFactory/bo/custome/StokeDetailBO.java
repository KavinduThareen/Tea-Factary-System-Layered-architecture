package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;

import java.sql.Date;
import java.sql.SQLException;

public interface StokeDetailBO extends SuperBO {
    boolean detail(String pid, String lid, Date date) throws SQLException, ClassNotFoundException;
}
