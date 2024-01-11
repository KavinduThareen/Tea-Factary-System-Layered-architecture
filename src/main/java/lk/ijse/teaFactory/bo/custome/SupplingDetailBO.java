package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;

import java.sql.Date;
import java.sql.SQLException;

public interface SupplingDetailBO extends SuperBO {
    boolean detail(String sid, String id, Date sDate) throws SQLException, ClassNotFoundException;
}
