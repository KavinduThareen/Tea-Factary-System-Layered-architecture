package lk.ijse.teaFactory.dao.customer.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.customer.StokeDetailDAO;

import java.sql.Date;
import java.sql.SQLException;

public class StokeDetailDAOImpl implements StokeDetailDAO {
    @Override
    public boolean detail(String pid, String lid, Date date) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stoke_detailse VALUES(?, ?,?)",pid,lid,date);
    }
}
