package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.Entity.LoginDetails;
import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.LoginDetailsDAO;
import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public class LoginDetailDAOImpl implements LoginDetailsDAO {
    @Override
    public boolean logdetail(LoginDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO login_details VALUES(?, ?, ?)",entity.getId(),entity.getInTime(),entity.getDate());
    }
}
