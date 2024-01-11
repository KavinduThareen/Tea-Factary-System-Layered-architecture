package lk.ijse.teaFactory.bo.custome.Impl;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.bo.custome.OtpBO;
import lk.ijse.teaFactory.dao.DAOFactory;
import lk.ijse.teaFactory.dao.custome.OtpDAO;
import lk.ijse.teaFactory.dto.OtpDto;

import java.sql.SQLException;

import static lk.ijse.teaFactory.dao.DAOFactory.DAOType.OTP;

public class OtpBOImpl implements OtpBO {
    OtpDAO otpDAO = (OtpDAO) DAOFactory.getDaoFactory().getDAO(OTP);
    @Override
    public boolean save(OtpDto dto) throws SQLException, ClassNotFoundException {
        return otpDAO.save(new Otp(dto.getOtp()));
    }

    @Override
    public int load() throws SQLException, ClassNotFoundException {
        return otpDAO.load();
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return otpDAO.delete(id);
    }
}
