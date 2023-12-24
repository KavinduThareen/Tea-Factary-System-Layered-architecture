package lk.ijse.teaFactory.dao.customer;

import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public interface LoginDetailsDAO {
     boolean logdetail(final LoginDetailsDto dto) throws SQLException, ClassNotFoundException;
}
