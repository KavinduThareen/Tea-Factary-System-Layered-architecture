package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.SupplingDetailDAO;

import java.sql.Date;
import java.sql.SQLException;

public class SupplingDetailDAOImpl implements SupplingDetailDAO {
    @Override
    public boolean detail(String sid, String id, Date sDate) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute( "INSERT INTO suppling_detailse VALUES(?, ?,?)",sid,id,sDate);
    }
}
