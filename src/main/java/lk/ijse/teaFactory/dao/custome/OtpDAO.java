package lk.ijse.teaFactory.dao.custome;

import lk.ijse.teaFactory.Entity.Otp;
import lk.ijse.teaFactory.dao.CrudDAO;

import java.sql.SQLException;

public interface OtpDAO extends CrudDAO<Otp> {
    int load() throws SQLException, ClassNotFoundException;
     boolean delete(int id) throws SQLException, ClassNotFoundException;
}
