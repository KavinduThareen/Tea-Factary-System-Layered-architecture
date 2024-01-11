package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean logdetail(final LoginDetailsDto dto) throws SQLException, ClassNotFoundException;
}
