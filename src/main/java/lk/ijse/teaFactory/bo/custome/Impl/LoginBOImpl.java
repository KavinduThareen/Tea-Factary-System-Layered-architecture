package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.LoginDetails;
import lk.ijse.teaFactory.bo.custome.LoginBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.LoginDetailsDAO;
import lk.ijse.teaFactory.dto.LoginDetailsDto;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    LoginDetailsDAO loginDetailsDAO = (LoginDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGINDETAIL);
    @Override
    public boolean logdetail(LoginDetailsDto dto) throws SQLException, ClassNotFoundException {
        return loginDetailsDAO.logdetail(new LoginDetails(dto.getId(),dto.getInTime(),dto.getDate()));
    }
}
