package lk.ijse.teaFactory.bo.custome;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.bo.SuperBO;
import lk.ijse.teaFactory.dto.OtpDto;

import java.sql.SQLException;

public interface OtpBO extends SuperBO {
    public boolean save(OtpDto dto) throws SQLException, ClassNotFoundException;
    int load() throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
}
