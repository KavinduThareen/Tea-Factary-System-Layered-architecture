package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.LoginDetails;
import lk.ijse.teaFactory.controller.LoginPageController;
import lk.ijse.teaFactory.dao.SuperDAO;
import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public interface LoginDetailsDAO extends SuperDAO {
     boolean logdetail(final LoginDetails entity) throws SQLException, ClassNotFoundException;
}
