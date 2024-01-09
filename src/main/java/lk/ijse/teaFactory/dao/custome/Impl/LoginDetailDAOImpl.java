package lk.ijse.teaFactory.dao.custome.Impl;

import lk.ijse.teaFactory.dao.SQLUtil;
import lk.ijse.teaFactory.dao.custome.LoginDetailsDAO;
import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public class LoginDetailDAOImpl implements LoginDetailsDAO {
    @Override
    public boolean logdetail(LoginDetailsDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO login_details VALUES(?, ?, ?)",dto.getId(),dto.getInTime(),dto.getDate());
    }
}
